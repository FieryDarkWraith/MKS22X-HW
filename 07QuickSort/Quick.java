import java.util.Arrays;
import java.util.Random;

public class Quick {
    private static Random r = new Random();

    public static int partitionOld(int[]ary, int left, int right){
	int startIndex = r.nextInt(right-left+1)+left;
	
	if(left == right){
	    return left;
	}

	int checker = ary[startIndex];
	int origRight = right;

	//set centerPiece aside
	swap(ary,startIndex,right);
	right--;

	//Until we reach last element, compare the leftmost element to centerPiece. If greater, swap.
	while(left != right){	    
	    if(ary[left]<=checker){
		left++;
	    }else{
		swap(ary,left,right);
		right--;
	    }
	}
	
	//Check last element with centerPiece. If it is less, then swap with element to right. Otherwise, swap with left.
	if(ary[left]<=checker){
	    swap(ary,right+1,origRight);
	    return right+1;
	}else{
	    swap(ary,left,origRight);
	    return left;
	}

	
    }

    public static void swap(int[]ary,int firstDex, int secondDex){
	int store = ary[firstDex];
	ary[firstDex]=ary[secondDex];
	ary[secondDex]=store;
    }

    
    public static int quickSelectOld(int[]ary,int k){
	
	int left = 0;
	int right = ary.length -1;
	int numIndex = -1;
	int n;
	do{
	    //System.out.println(left+","+right);
	    n = partitionOld(ary,left,right);
	    //System.out.println(n);
	    if(n < k){
		left = n+1;
	    }else{
		right = n-1;
	    }
	}while(n != k);

	return ary[n];
	
    }

    public static void quickSortOld(int[]ary){
	quickSortOld(ary,0,ary.length-1);
    }

    private static void quickSortOld(int[]ary,int left,int right){
	if(left< right){
	    int index = partitionOld(ary,left,right);
	    quickSortOld(ary,left,index-1);
	    quickSortOld(ary,index+1,right);
	}
    }
    /*
    public static int[] partitionTEST(int[]ary,int left,int right){
	if(left == right){
	    int[]end = {left,left};
	    return end;
	}
	
	int pivotIndex = r.nextInt(right-left+1)+left;
	int checker = ary[pivotIndex];

        int count = 1;
	int origLeft = left+1;
	swap(ary,left,pivotIndex);
	left++;

	//System.out.println(left+","+right);	
	
	while(left < right){
	    if(ary[left] < checker){
		left++;
	    }else if(ary[left] == checker){
		count++;
		swap(ary,origLeft,left);
		origLeft++;
	    }else{
		swap(ary,left,right);
		right--;
	    }
	    //System.out.println("Was here");
	}

	int[]end = new int[2];
	if(ary[left] <= checker){
	    for(int i=0;i<count;i++){
		swap(ary,left-i,origLeft-i);
	    }
	    end[0]= left-count;
	    end[1]= left;
	}else{
	    for(int i=1;i<count+1;i++){
		swap(ary,left-i,origLeft-i);
	    }
	    end[0]=right-count;
	    end[1]=right;
	}
	
	return end;
    }

    public static int[] partitionFAIL(int[]ary,int left,int right){
	if(left==right){
	    int[] end = {left,left};
	    return end;
	}

	int pivot = r.nextInt(right-left+1)+left;
        int checker = ary[pivot];
	int[] copy = new int[right-left+1];
	int cpL =0;
	int cpR = copy.length-1;
	int orL = left;
	int orR = right;
	int count = 0;
	
	while(left<right){
	    if(ary[left]<checker){
		copy[cpL]=ary[left];
		left++;
		cpL++;
	    }else if(ary[left]==checker){
		count++;
		left++;
	    }else{
		copy[cpR]=ary[left];
		left++;
		cpR--;
	    }
	}
	//System.out.println(cpL+","+cpR);

	if(ary[left]<checker){
	    copy[cpL] = ary[left];
	    cpL++;
	    //System.out.println(Arrays.toString(copy));
	}else if(ary[left]==checker){
	    copy[cpL]=ary[left];
	    count++;
	}else{
	    copy[cpR] = ary[left];
	    cpR--;
	    // System.out.println(Arrays.toString(copy));
	}
	
	for(int i=cpL;i<cpL+count;i++){
	    copy[i]=checker;
	}

	for(int i=0;i<copy.length;i++){
	    ary[orL+i]=copy[i];
	}
	
	int[]end = {};
	return end;
	
    } ALL of this are failed prototypes. 
    */
    public static int[] partition(int[]ary,int left,int right){
	int[]end = new int[2];
	if(left == right){
	    end[0] = left;
	    end[1] = right;
	    return end;
	}
	
	int index = r.nextInt(right-left+1)+left;
	int pivot = ary[index];
	int count = 0;
	int origLeft = left;
	int[]copy = new int[right-left+1];
	int cpL =0;
	int cpR =copy.length-1;
	
	
	while(left <= right){
	    if(ary[left] < pivot){
		copy[cpL]=ary[left];
		left++;
		cpL++;
	    }else if(ary[left] > pivot){
		copy[cpR]=ary[left];
		left++;
		cpR--;
	    }else{
		count++;
		left++;
	    }
	}

	int copyOfcpL = cpL;
	while(count > 0){
	    copy[copyOfcpL]=pivot;
	    copyOfcpL++;
	    count--;
	}
	
	int n = 0;
	for(int i=origLeft;i<right+1;i++){
	    ary[i]=copy[n];
	    n++;
	}
	
	end[0] = cpL+origLeft;
	end[1] = cpR+origLeft;
	return end;	
	
    }

    public static void quickSort(int[]ary){
	quickSort(ary,0,ary.length-1);
    }

    private static void quickSort(int[]ary,int left, int right){
	
	if(left<right){
	    //System.out.println("iteration"+left+","+right);
	    int[]dexes = partition(ary,left,right);
	    //System.out.println(Arrays.toString(dexes));
	    quickSort(ary,left,dexes[0]-1);
	    quickSort(ary,dexes[1]+1,right);	    
	}
    }
    
    

    public static String name(){
	return "6,Zhang,Kevin";
    }
    
    public static void main(String[]args){
	int[]test1 = {1,2,2,2,3,4,7,2,3,4,2,3,2};
	int[]test2 = new int[4000000];
	int[]tester = new int[test2.length];
	boolean run = false;
	//System.out.println(Arrays.toString(test2));
	for(int i=0;i<test2.length;i++){
	    //test1[i] = r.nextInt(3);
	    test2[i] = r.nextInt(Integer.MAX_VALUE);
	    tester[i] = test2[i];
	}
	//System.out.println(Arrays.toString(test2));
	/*
	if(run){		
	    quickSortOld(test1);
	}else{
	    Arrays.sort(test1);
	}
	*/
	//System.out.println(Arrays.toString(partition(test1,0,test1.length-1)));
	//System.out.println(partitionOld(test1,0,test1.length-1));
	quickSort(test2);
	Arrays.sort(tester);
	System.out.println(Arrays.equals(test2,tester));
	//System.out.println(Arrays.toString(test2)); 
    }

    
}
