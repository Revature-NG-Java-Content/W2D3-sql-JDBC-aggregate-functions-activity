public class Main {

    public static void main(String[] args) {
        ProduceRepo pRepo = new ProduceRepo();
        System.out.println("Main method COUNT: " + pRepo.getCount());

        System.out.println("Main method SUM: " + pRepo.getSum());
    }
}
