package javapractice;

public class MyNode<K> implements INode<K>{
    private K key;
    private MyNode next;

    public MyNode(K key){
        this.key = key;
        this.next= null;
    }
    @Override
    public K getKey(){
        return key;
    }
    @Override
    public void setKey(K key){
        this.key = key;
    }

    @Override
    public INode<K> getNext() {
        return next;
    }

    public void setNext(INode next) {
        this.next = (MyNode<K>) next;
    }

    @Override
    public String toString() {
        StringBuilder myNodeString = new StringBuilder();
        myNodeString.append("MyNode{" + "key=").append(key).append('}');
        if (next != null)
            myNodeString.append(next);
        return myNodeString.toString();
    }
}