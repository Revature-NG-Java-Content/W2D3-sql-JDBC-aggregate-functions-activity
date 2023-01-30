
## W2D3 - Group activity: perform an aggregation in a JDBC query. 

# What are Aggregate Funtions ?

### An aggregate function computes a single result from multiple input rows. For example, we will start with basic aggregates to compute the:


#### COUNT - Returns the number of rows in a database table
#### SUM - Returns the total sum of a numeric column
#### AVG - Calculates the average of a set of values

#### MIN - Returns the lowest value (minimum) in a set of non-NULL values
#### MAX - Returns the highest value (maximum) in a set of non-NULL values

##### \n 

## COUNT() 

### Syntax:

COUNT(*)  

or  

COUNT( [ALL|DISTINCT] expression )  

For this example, lets keep it simple. Copy the following lines into Dbeaver to create a table in your DB:

```
create table if not EXISTS produce ( 
id serial primary key, 
pname varchar(20) not null unique, 
price decimal(3,2), 
ptype varchar(10) not null,
quantity int NOT null
);

```

Once you have created the table copy, paste and run the following lines into dbeaver to populate the 'produce' table you created. 

```
insert into produce (pname, price, ptype, quantity) values ('navel orange', 1.99, 'citrus',5),
('mandarin orange', 0.75, 'citrus',4),
('tangerine', 0.50, 'citrus',3), 
('red delicious', 2.00, 'apple',1),
('jona gold', 2.50, 'apple',7), 
('granny smith', 1.00, 'apple',2), 
('blueberry', 0.40, 'berry',4),
('raspberry', 0.35, 'berry',7), 
('kiwi', 0.75, 'berry',8), 
('watermelon', 3.99, 'melon',3),
('cantaloupe', 2.99, 'melon',8), 
('honeydew', 2.00, 'melon',9), 
('lettuce', 2.99, 'leafy',8),
('spinach', 1.99, 'leafy',7), 
('pumpkin', 4.99, 'marrow',3), 
('cucumber', 0.99, 'marrow',2), 
('potato', 0.45, 'root',1), 
('yam', 0.25, 'root',5),
('sweet potato', 0.50, 'root',7),
('onion', 0.33, 'allium',2),
('garlic', 0.25, 'allium',6), 
('shallot', 0.60, 'allium',1);

```

Awesome ! For this first example we are going to complete our our first aggregate function in our SQL script. 

Task 1: Use the COUNT(*) function to get the number of differnt produce in the table

Copy and paste the following line into dbeaver and run it :

```
SELECT COUNT(*) 
FROM produce

```

What answer is printed into the console? 

You should see 22 printed in the console.


		** // here i will add in a screenshot of the solution they should see ()


Awesome! Pretty straight forward right ? Great!  Now let's take it up a notch !
If you scroll to the top of this lesson you'll see that the goal of this excersise if to perform aggregate functions in a JDBC (Java Database Connectivity) Query! 
How exciting !! So lets do just that ! 


- Create a new Java Project in your IDE (IntelliJ, Eclipse, etc - note: I created a Maven project)

- Name it whatever you like - I went with ' AggregateFunctionsActivity ' 

- Next we will set up our project to communicate with our DB


	
	- In our 'java' folder , right click to Create a new Java class. Call it 'Produce'

	- Then create a new interface in the 'java' folder, Call it "ProduceDAO"

	- Next, create a third new file which will be a Java Class. Call it "ProduceRepo"

	- Finally create a Main/Driver class and in that main class add your main method

			** // will add images with example project files



Take a Few moments to connect to your database! The fast and easy way that I did it was to connect to my localhost right in the constructor of my ProduceRepo Class. Remember every time we create an instance of a class our constructor is called so when we create an instance of this class we will make the connection to our database!

```

  Connection conn ;

    public ProduceRepo() {

        String url = "jdbc:postgresql://localhost:5433/postgres?currentSchema=public";
        String username = "postgres";
        String password = "password";

        try {

            conn = DriverManager.getConnection(url,username,password);

        }catch(SQLException sqlException){

           System.out.println(sqlException);
        }

    }


```

You're doing great ! Let's use the same count example from earlier ! In your ProduceRepo class add the following code:

Please keep in mind that we created the ProduceDAO interface that our Produce Repo has implemented, which is why we see that @Override annotation

```
@Override
    public int getCount() {

        String sql = "SELECT COUNT(*) FROM produce";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt("count");


        } catch (SQLException sqlException) {
            System.out.println("Count function error: "+sqlException);
        }
        return 0;
    }
```

Notes from the above code snippet :
	sql string = The String "sql" represents the SQL statement that we want to execute \n
	rs.next() = remember, in order for us to see what is in the result set we get back from the database, must use rs.next() which allows to go from 
		pointing to the result set to being inside the result set. \n 
	return = Note rs.getInt() indicates we are expecting an int back from the db. Inside of the parenthesis we add the column name or number where our 
		answer will be. Since our aggregate functions return a single result, the column name insode those parenthesis should match the
		aggregate function you are executing - as we see above.  The second return is there if we dont get a result becak , but we didnt have ann 
		error either. Returning 0 just satifies the function's signature. \n


Now let's see what we get...


In your Main class add the following code snippet:

```
public class Main {

    public static void main(String[] args) {
    
    	// create a new instance of your ProduceRepo so we can connect to the db and call the functions we are creating
        ProduceRepo pRepo = new ProduceRepo();
	
	
	// below I just added a print statement to print the result as well as remind me where the print statement is coming from
	
        System.out.println("Main method COUNT: " + pRepo.getCount());
    }
}

```

## SUM()

Heyyy! You're killing it ! Let's switch it up a bit and use our SUM() function! For this example I am only going to walk you through finding the solution in dbeaver and leave it to you to do it in Java like we did with our COUNT() function!


In dbeaver, copy and paste the followling lines and run it:

```
SELECT SUM(quantity) 
FROM produce 
WHERE ptype = 'berry';
```

Let's break this down real quick! Notice we used SUM(quantity) instead of adding the asterisk symbol like we did with COUNT(), this is because if we recall from the definitions above SUM returns the total sum of a numeric column which means that we have to specify which numeric column we want to add up and then return that value; In this case we are returning the sum of the quantity column.  Also, to make it interesting we are only adding the quanties of the produce WHERE ptype or produce type = "berry". 

Well did you run it ? What sum did you get in dbeaver ? It should be 22 if you're following along with me exactly ! 

Awesome !! If you want to check the quantity of all produce in the table, how would you do that ??

Hint: Maybe we just need to remove a clause ?



YOU ARE THE BOMB !!!! 



## Self Practice !

Now that we've used Java to execute our COUNT() function ! Let's do the same for our SUM() function !

Below you'll find an incomplete code snippet, complete the code to get the sum of all produce WHERE the ptype is "berry" 

```
@Override
    public int getSum() {

        // note you cannot add the (*) here because it will through an exception
        // you must include what column you want to "sum" up in your Query
	
        String sql = ""

        try {
             TODO: 
	     - create sql statement
	     - execute sql statement
	     - return result
	     


        } catch (SQLException sqlException) {
	
            System.out.println("Sum function error: "+sqlException);
	    
        }
        return 0;
    }


```

After you complete the code, make your way to your Main method and test it out !

What did you get ??? If youre following me exactly, the answer should be 19! 

 	
Awesome to continue self practice complete the following:

	- Find the Average quantity of produce in the table. Meaning, on average how many of each produce is there?
	
	- Find the Maximum quantity from produce
	
	- Find the Minimum quantity from produce
	
	
Hint: Try doing it as we did earlier. Find the answer in dbeaver, then translate it to Java. 

Good Luck and GREAT JOB! 







