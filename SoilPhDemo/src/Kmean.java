import java.util.Random;

public class Kmean {
	public int[] Kmean(int k,int array[][]) {
		// TODO Auto-generated constructor stub
		int centroid[][]= new int[k][2];
		int mean[][]=new int[k][2];
		int sum_a[]=new int[k];
		int sum_b[]=new int[k];
		int count[]=new int[k];
		int distance[]=new int[k];
		int array_size=array.length;
		int cluster[]=new int[array_size];
			mean[0][0]=80;
			mean[0][1]=67;
			mean[1][0]=-86;
			mean[1][1]=83;
			mean[2][0]=79;
			mean[2][1]=-107;
			
		do
		{
			for(int i=0;i<k;i++)
			{
				centroid[i]=mean[i];
			}
			for(int i=0;i<array_size;i++)
			{
				for(int j=0;j<k;j++)
				{
					distance[j]=Euclidean(centroid[j][0],array[i][0],centroid[j][1],array[i][1]);
					
				}
				cluster[i]=getMinLocation(distance)+1;
				
			}
			for(int j=0;j<k;j++)
			{
				for(int i=0;i<array_size;i++)
				{
					if(cluster[i]==j+1)
					{
						sum_a[j]=sum_a[j]+array[i][0];
						sum_b[j]=sum_b[j]+array[i][1];
						count[j]++;
					}
				}
				mean[j][0]=(int) ((float)sum_a[j]/count[j]);
				mean[j][1]=(int) ((float)sum_b[j]/count[j]);
				System.out.println("mean["+j+"]="+mean[j][0]+" b:"+mean[j][1]);
			}
		}while(isequal(k,centroid,mean));
		return cluster;
	}
	public boolean isequal(int k,int a[][],int b[][])
	{
		boolean flag=false;
		for(int i=0;i<k;i++)
		{
			if(a[i]!=b[i])
				flag=true;
		}
		return flag;
	}
	public int random()
	{
		Random random = new Random(); 
		int value = random.nextInt(257)-128;
		return value;
	}
	public int Euclidean(int x1,int x2,int y1,int y2)
	{
		int  xDiff = x1-x2;
		int  xSqr  = (int) Math.pow(xDiff, 2);

		int yDiff = y1-y2;
		int ySqr = (int) Math.pow(yDiff, 2);

		int diff   = (int) Math.sqrt(xSqr + ySqr);
		return diff;
	}

	public int getMinLocation(int[] inputArray){ 
		int location=0, minValue=999; 
		for(int i=0;i<inputArray.length;i++){ 
			if(inputArray[i] < minValue){ 
				minValue = inputArray[i];
				location=i;
			} 
		} 
		return location; 
	} 
}
