package javapractice;

import java.util.ArrayList;

public class MyLinkedHashMap<K, V> {
    private final  int numBuckets;
    ArrayList<MyLinkedList<K>> myBucketsArray;

    public MyLinkedHashMap(){
        this.numBuckets = 10;
        this.myBucketsArray = new ArrayList<>(numBuckets);
        //create empty LinkedList
        for(int i =0;i<numBuckets;i++)
            this.myBucketsArray.add(null);
    }

    private int getBucketIndex(K key){
        int hashCode = Math.abs(key.hashCode());
        int index = hashCode % numBuckets;
        System.out.println("Key: " +key+ " hashCode: " +hashCode+ " Index: " +index);
        return index;
    }


    public V get(K key) {
        int index = this.getBucketIndex(key);
        MyLinkedList<K> myLinkdList = this.myBucketsArray.get(index);
        if(myLinkdList == null) return null;
        MyMapNode<K,V> myMapNode = (MyMapNode<K, V>) myLinkdList.search(key);
        return (myMapNode == null) ? null : myMapNode.getValue();
    }

    public void add(K key, V value) {
        int index = this.getBucketIndex(key);
        MyLinkedList<K> myLinkdList = this.myBucketsArray.get(index);
        if(myLinkdList == null) {
            myLinkdList = new MyLinkedList<>();
            this.myBucketsArray.set(index, myLinkdList);
        }

        MyMapNode<K,V> myMapNode = (MyMapNode<K, V>) myLinkdList.search(key);
        if(myMapNode == null){
            myMapNode = new MyMapNode<>(key,value);
            myLinkdList.append(myMapNode);
        }else {
            myMapNode.setValue(value);
        }

    }
    @Override
    public String toString() {
        return "MyLinkedHashMap List{" + myBucketsArray +'}';
    }

    public INode remove(K deleteKey) {
        int index = this.getBucketIndex(deleteKey);
        MyLinkedList<K> myLinkedList = this.myBucketsArray.get(index);
        if(myLinkedList == null) return null;
        MyMapNode<K,V> previousNode = (MyMapNode<K, V>) myLinkedList.head;
        MyMapNode<K,V> currentNode = (MyMapNode<K, V>) myLinkedList.head;
        INode tempNode=null;
        if(currentNode != null && currentNode.getKey().equals(deleteKey)) {
            tempNode=currentNode;
            myLinkedList.head=currentNode.getNext();
            return tempNode;
        }
        while(currentNode != null && !(currentNode.getKey().equals(deleteKey))) {
            previousNode=currentNode;
            currentNode= (MyMapNode<K, V>) currentNode.getNext();
        }
        tempNode=currentNode;
        previousNode.setNext(currentNode.getNext());
        return tempNode;
    }
}
