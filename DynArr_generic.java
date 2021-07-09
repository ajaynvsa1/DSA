class Main {
  public static void main(String[] args) {
    // Create dynArr of string with capacity of 5
    dynArr<String> x = new dynArr<String>(5);
    // Add numbers beyond capacity
    for(int i = 1; i < 11; i++){
      String c = Integer.toString(i);
      x.add(c);
    }
    //Print dynArr
    System.out.println(x);
    //Remove element at position 4 i.e value 5
    x.remove(4);
    System.out.println(x);
    //Remove element at position 7
    x.remove(7);
    System.out.println(x);
  }
}

class dynArr <T> {
  //Tracks the number of elements in the dynArr
  //Index at which element is added
  private int len;

  //Tracks possible number of elements array can hold without reallocation
  private int capacity;

  private T contents[];
  public dynArr(int size){
    len = 0;
    capacity=size;
    contents = (T[])(new Object[size]);
    for(int i = 0; i < size; i++){
      contents[i] = null;
    }
  }

  //Adds an element at index this.len
  public void add(T val){
    int asdf = contents.length;
     //Capacity is less than index at which to add
      if(len>=asdf){
        //Array temp initialized with capacity + 1 length to accommodate val  
        T[] temp = (T[])(new Object[contents.length+1]);
        for(int i = 0; i < contents.length; i++){
          temp[i] = contents[i];
        }
        temp[contents.length] = val;

        //Doubling contents length so as not to increase capacity every time element is added
        contents = (T[])(new Object[temp.length*2]);
        for(int i = 0; i < contents.length; i++){
          if(i < temp.length){
            contents[i] = temp[i];
          }
          else{
            contents[i] = null;
          }
        }
      }
      else{
       // No reallocation needed  
        contents[len] = val;
      }
      len+=1;
  }

  /* 
   * Precondition: 0<=index<capacity
   */
   public T remove(int index){
    //Storing removed val to return
    T val = contents[index];
    //Set to null
    contents[index] = null;
    //Iteratively copy succeding element to current position len+1 - index times (copy a placeholder over as well)
    for(int i = index; i < len-1; i++){
      contents[i] = contents[i+1];
    }
     //Decrement len
    len--;
    return val;
  }
  
 //Removes first occurence of val
  public int removeVal(T val){
    int ind = indexOf(val);
    if(ind != -1){
      this.remove(ind);
    }
    return ind;
  }


  public int indexOf(T val){
    for(int i = 0; i < len; i++){
      if(contents[i] == val){
        return i;
      }
    }
    return -1;
  }
  
  // Returns val @ index
  public T get(int index){
    return contents[index];
  }

  // Sets val @ index
  public void set(int index,T val){
    contents[index] = val;
  }

  // Returns contents as String in form of "[<element1> , ... , <elementlen>]"
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for(int i = 0; i < len-1; i++){
        sb.append(contents[i]);
        sb.append(" , ");
    }
    sb.append(contents[len-1]);
    sb.append("]");        
    return sb.toString();
}

  public boolean isEmpty(){
    return len == 0;
  }

  public boolean contains(T val){
    return this.indexOf(val)!=-1;
  }
  public int size(){
    return len;
  }
}
