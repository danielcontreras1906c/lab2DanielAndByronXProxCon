import Data.Container;
public class ContainerReviewProbe {
    public static void main(String[] args) throws Exception {
        Container c = new Container();
        c.produce(7);
        c.setAmount(99);
        c.consume();
        Container initial = new Container(5);
        initial.produce(8);
        initial.consume();
    }
}
