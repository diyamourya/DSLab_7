class L7S24 
{ 
    int st[]; 
    int maxarr[]; 
    int sumarr[];
    
    void constructSTsum(int arr[], int n) 
	{ 
		int x = (int) (Math.ceil(Math.log(n) / Math.log(2))); 

		int max_size = 2 * (int) Math.pow(2, x) - 1; 

		sumarr = new int[max_size];
		constructSTUtilsum(arr, 0, n - 1, 0); 
    }
    
	int minVal(int x, int y) { 
		return (x < y) ? x : y; 
	} 

	int getMid(int s, int e) { 
		return s + (e - s) / 2; 
	} 

	int RMQUtil(int ss, int se, int qs, int qe, int index) 
	{ 
		if (qs <= ss && qe >= se) 
			return st[index]; 

		if (se < qs || ss > qe) 
			return Integer.MAX_VALUE; 

		int mid = getMid(ss, se); 
		return minVal(RMQUtil(ss, mid, qs, qe, 2 * index + 1), 
				RMQUtil(mid + 1, se, qs, qe, 2 * index + 2)); 
	} 

	int RMQ(int n, int qs, int qe) 
	{ 
		if (qs < 0 || qe > n - 1 || qs > qe) { 
			System.out.println("Invalid Input"); 
			return -1; 
		} 

		return RMQUtil(0, n - 1, qs, qe, 0); 
	} 

	int constructSTUtil(int arr[], int ss, int se, int si) 
	{ 
		if (ss == se) { 
			st[si] = arr[ss]; 
			return arr[ss]; 
		} 

		int mid = getMid(ss, se); 
		st[si] = minVal(constructSTUtil(arr, ss, mid, si * 2 + 1), 
				constructSTUtil(arr, mid + 1, se, si * 2 + 2)); 
		return st[si]; 
	} 

	void constructST(int arr[], int n) 
	{ 

		int x = (int) (Math.ceil(Math.log(n) / Math.log(2))); 

		int max_size = 2 * (int) Math.pow(2, x) - 1; 
		st = new int[max_size]; 

		constructSTUtil(arr, 0, n - 1, 0); 
    } 
    


    int maxVal(int x, int y) { 
        if(x>y)
            return x;
        return y;
    } 
    
    int RMQUtilmax(int ss, int se, int qs, int qe, int index) 
	{ 
		if (qs <= ss && qe >= se) 
			return maxarr[index]; 

		if (se < qs || ss > qe) 
			return Integer.MIN_VALUE; 

		int mid = getMid(ss, se); 
		return maxVal(RMQUtilmax(ss, mid, qs, qe, 2 * index + 1), RMQUtilmax(mid + 1, se, qs, qe, 2 * index + 2)); 
    }
    
    int RMQmax(int n, int qs, int qe) 
	{ 
		if (qs < 0 || qe > n - 1 || qs > qe) { 
			System.out.println("Invalid Input"); 
			return -1; 
		} 

		return RMQUtilmax(0, n - 1, qs, qe, 0); 
    } 
    
    int constructSTUtilmax(int arr[], int ss, int se, int si) 
	{ 
		
		if (ss == se) { 
			maxarr[si] = arr[ss]; 
			return arr[ss]; 
		} 

		int mid = getMid(ss, se); 
		maxarr[si] = maxVal(constructSTUtilmax(arr, ss, mid, si * 2 + 1),constructSTUtilmax(arr, mid + 1, se, si * 2 + 2)); 
		return maxarr[si]; 
    }
    
    void constructSTmax(int arr[], int n) 
	{ 
		int x = (int) (Math.ceil(Math.log(n) / Math.log(2))); 

		int max_size = 2 * (int) Math.pow(2, x) - 1; 
        maxarr = new int[max_size]; 
        
		constructSTUtilmax(arr, 0, n - 1, 0); 
    }
    
    int getSumUtilsum(int ss, int se, int qs, int qe, int si) 
	{ 
		if (qs <= ss && qe >= se) 
			return sumarr[si]; 

		if (se < qs || ss > qe) 
			return 0; 

		int mid = getMid(ss, se); 
		return getSumUtilsum(ss, mid, qs, qe, 2 * si + 1) + getSumUtilsum(mid + 1, se, qs, qe, 2 * si + 2); 
    }
    
    void updateValueUtilsum(int ss, int se, int i, int diff, int si) 
	{ 
		if (i < ss || i > se) 
			return; 

		sumarr[si] = sumarr[si] + diff; 
		if (se != ss) { 
			int mid = getMid(ss, se); 
			updateValueUtilsum(ss, mid, i, diff, 2 * si + 1); 
			updateValueUtilsum(mid + 1, se, i, diff, 2 * si + 2); 
		} 
    }
    
    void updateValuesum(int arr[], int n, int i, int new_val) 
	{ 
		if (i < 0 || i > n - 1) { 
			System.out.println("Invalid Input"); 
			return; 
		} 

		int diff = new_val - arr[i]; 

		arr[i] = new_val; 

		updateValueUtilsum(0, n - 1, i, diff, 0); 
	} 

    int getSum(int n, int qs, int qe) 
	{ 
		if (qs < 0 || qe > n - 1 || qs > qe) { 
			System.out.println("Invalid Input"); 
			return -1; 
		} 
		return getSumUtilsum(0, n - 1, qs, qe, 0); 
    } 
    
    int constructSTUtilsum(int arr[], int ss, int se, int si) 
	{ 
		if (ss == se) { 
			sumarr[si] = arr[ss]; 
			return arr[ss]; 
		} 

		int mid = getMid(ss, se); 
		sumarr[si] = constructSTUtilsum(arr, ss, mid, si * 2 + 1) + constructSTUtilsum(arr, mid + 1, se, si * 2 + 2); 
		return sumarr[si]; 
    }
    

	public static void main(String args[]) 
	{ 
		int arr[] = {1, 3, 2, 7, 9, 11}; 
		int n = arr.length; 
        L7S24 tree = new L7S24(); 
        L7S24 tree2 = new L7S24(); 
        L7S24 tree3 = new L7S24(); 

        tree.constructST(arr, n); 
        tree2.constructSTmax(arr, n);
        tree3.constructSTsum(arr, n); 

		int qs = 1; 
        int qe = 5; 

        System.out.println("Minimum of values in range [" + qs + ", "+ qe + "] is = " + tree.RMQ(n, qs, qe)); 
        System.out.println("Maximum of values in range [" + qs + ", "+ qe + "] is = " + tree2.RMQmax(n, qs, qe)); 
        System.out.println("Sum of values in given range = " + tree3.getSum(n, 1, 5));

        tree3.updateValuesum(arr, n, 0, 5); 
        tree3.updateValuesum(arr, n, 1, 7);
        tree3.updateValuesum(arr, n, 2, 6);
        tree3.updateValuesum(arr, n, 3, 11);
        tree3.updateValuesum(arr, n, 4, 13);
        tree3.updateValuesum(arr, n, 5, 15);

        System.out.println("Updated sum of values in given range = " + tree3.getSum(n, 1, 5)); 

	} 
} 
