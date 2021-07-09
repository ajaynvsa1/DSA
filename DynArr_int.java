class Main {
  public static void main(String[] args) {
    // Create dynArr of capacity of 5
    dynArr x = new dynArr(5);
    // Add numbers beyond capacity
    for(int i = 1; i < 11; i++){
      x.add(i);
    }
    //Print dynArr
    System.out.println(x);
    //Remove element at position 4 i.e value 5
    x.remove(4);
    System.out.println(x);
    //Remove element of value 
    x.removeVal(7);
    System.out.println(x);
  }
}
class dynArr {
  //Tracks the number of elements in the dynArr
  //Index at which element is added
  private int len;

  //Tracks possible number of elements array can hold without reallocation
  private int capacity;
  private int[] contents;
  public dynArr(int size){
    len = 0;
    capacity=size;
    contents = new int[size];
    for(int i = 0; i < size; i++){
      contents[i] = 2147483647;
    }
  }

  //Adds an element at index this.len
  public void add(int val){
    int asdf = contents.length;
      //Capacity is less than index at which to add
      if(len>=asdf){
        //Array temp initialized with capacity + 1 length to accommodate val  
        int[] temp = new int[contents.length+1];
        for(int i = 0; i < contents.length; i++){
          temp[i] = contents[i];
        }
        temp[contents.length] = val;
        //Doubling contents length so as not to increase capacity every time element is added
        contents = new int[temp.length*2];
        for(int i = 0; i < contents.length; i++){
          if(i < temp.length){
            contents[i] = temp[i];
          }
          else{
            //Max int value used as placeholder for empty position  
            contents[i] = 2147483647;
          }
        }
      }
      else{
        // No reallocation needed  
        contents[len] = val;
      }
      capacity = contents.length;
      len+=1;
  }
  
  /*
   * Precondition: 0<=index<capacity
   */
  public int remove(int index){
    //Storing removed val to return
    int val = contents[index];
    //Replacing with placeholder
    contents[index] = 2147483647;
    //Iteratively copy succeding element to current position len+1 - index times (copy a placeholder over as well)
    for(int i = index; i < len-1; i++){
      contents[i] = contents[i+1];
    }
    //Decrement len
    len--;
    return val;
  }

  //Removes first occurence of val
  public int removeVal(int val){
    int ind = indexOf(val);
    if(ind != -1){
      this.remove(ind);
    }
    return ind;
  }
  public int indexOf(int val){
    for(int i = 0; i < len; i++){
      if(contents[i] == val){
        return i;
      }
    }
    return -1;
  }

  // Returns val @ index
  public int get(int index){
    return contents[index];
  }

  // Sets val @ index
  public void set(int index,int val){
    contents[index] = val;
  }

  // Returns contents as String in form of "[<element1> , ... , <elementlen>]"
  public String toString(){
    String result = "[";
    for(int i = 0; i < len-1; i++){
      result+=(Integer.toString(contents[i])+" , ");
    }
    result+=(Integer.toString(contents[len-1])+']');
    return result;
  }
  public boolean isEmpty(){
    return len == 0;
  }
  public boolean contains(int val){
    return this.indexOf(val)!=-1;
  }
  public int size(){
    return len;
  }
}
