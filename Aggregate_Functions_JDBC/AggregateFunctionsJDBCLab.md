# What are Aggregate Funtions ?

### An aggregate function computes a single result from multiple input rows. For example, we will start with basic aggregates to compute the:


#### COUNT - 
#### SUM -
#### AVG -

#### MIN -
#### MAX - 



### COUNT() 

## The COUNT() function returns the number of rows in a database table.

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

Copy and pase the following line into dbeaver and run it :

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



Take a Few moments to connect to your database! The fast and easy way that I did it was to connect to my localhost right in the constructor of my ProduceRepo Class :

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
	sql string
	rs.next()
	return 






 	










