package com.xl001;

public class MyArrayList{
    private int[] array;    //保存数据的空间
    private int size;       //保存有效数据个数

    MyArrayList(int capacity){
        this.array = new int[capacity];

    }
    public void pushFront(int item) {
        if(this.size == this.array.length){
            int capacity = array.length*2;
            int[] newArray = new int[capacity];

            for(int i=0; i<array.length; i++){
                newArray[i] = array[i];
            }

            this.array = newArray;
        }
        for(int i=this.size; i>=1; i--){
            this.array[i] = this.array[i-1];
        }
        array[0]=item;
        this.size++;
    }

    public void pushBack(int item){

    }
    public void popBack(){
        if(this.size==0){
            throw new Error();
        }
        this.array[--this.size]=0;
    }

    public void add(int index, int item){
        for(int i=this.size; i>index; i--){
            this.array[i] = this.array[i-1];
        }
        this.array[index] = item;
        this.size++;
    }
    public void remove(int index){
        for(int i=index; i<size-1;i++){
            this.array[i] = this.array[i+1];
        }
        this.size--;
    }

    //扩容
    private void ensureCapacity(){
        if(this.size<this.array.length){
            return;
        }

        //扩容为原来的两倍
        int capacity = this.array.length*2;
        int[] newArray = new int[capacity];

        for(int i=0; i<this.size; i++){
            newArray[i] = array[i];
        }

        this.array = newArray;
    }
}
