import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Object> x = new LinkedList<>();
        x.add("A");
        x.add("B");
        x.add("C");
        System.out.println(reverse_ll(x));

        Tree bt = new Tree();
        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);


        System.out.println(bt.getBase());
        bt.updateArrayList();
        System.out.println(bt.getLevel(1));
        System.out.println(bt.getLevel(2));
        System.out.println(bt.getLevel(3));
//        System.out.println(bt.getLevel(4));


    }


    public static LinkedList<Object> reverse_ll(LinkedList<Object> data){
        LinkedList<Object> new_data = new LinkedList();
        for (int i = data.size()-1; i >= 0; i--) {
            new_data.add(data.get(i));
        }
        return new_data;
    }
    
}

class Node{

    private int value;
    private Node Left;
    private Node Right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return Left;
    }

    public Node getRight() {
        return Right;
    }

    public void setLeft(Node left) {
        Left = left;
    }

    public void setRight(Node right) {
        Right = right;
    }



    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", Left=" + Left +
                ", Right=" + Right +
                '}';
    }
}

class Tree{
    private Node Base;
    private int max = Integer.MIN_VALUE;
    private ArrayList<ArrayList<Integer>> data = new ArrayList<>();
    public Node getBase() {
        return Base;
    }

    void add(int x){
        Base = addRecursive(Base,x);
        if(x>max){
            max = x;
        }
    }


    void updateArrayList(){
        data.removeAll(data);
        int levels =findLevel(max,getBase());
        for (int i = 0; i < levels+1; i++) {
            data.add(new ArrayList<>());
        }
        for (int i = 0; i <= max; i++) {
            data.get(findLevel(i, this.getBase())).add(i);
        }
//        System.out.println(data);
    }


    public ArrayList<Integer> getLevel(int Level){
        updateArrayList();
        if(Level > 0 && Level < data.size()){
            return data.get(Level);
        }
        return null;
    }

    private Node addRecursive(Node Current, int value){
        if(Current == null){
            return new Node(value);
        }
        if(value < Current.getValue()){
            Current.setLeft(addRecursive(Current.getLeft(),value));
        }else if(value > Current.getValue()){
            Current.setRight(addRecursive(Current.getRight(), value));
        }else return Current;
        return Current;
    }

    int findLevel(int Value, Node x){
        return FindLevelRecursive(x, Value,1);
    }

    private int FindLevelRecursive(Node node, int Value, int level) {
        if (node == null)
            return 0;

        if (node.getValue() == Value)
            return level;

        int Lower = FindLevelRecursive(node.getLeft(), Value, level + 1);

        if (Lower != 0) {
            return Lower;
        }

        Lower = FindLevelRecursive(node.getRight(), Value, level + 1);
        return Lower;
    }



}
