import java.util.Random;

public class Kmean1 {
	public int[] Kmean1(int k,int array[][]) {
		// TODO Auto-generated constructor stub
		int centroid[][]= new int[k][2];
		int mean[][]=new int[k][2];
		int sum_a[]=new int[k];
		int sum_b[]=new int[k];
		int count[]=new int[k];
		int sum,dis_index;
		int dis[]=new int[2];
		int array_size=array.length;
		int d2[]=new int[array_size];
		int d[]=new int[array_size];
		float p[]=new float[array_size];
		int distance[]=new int[k];
		int cluster[]=new int[array_size];
			int m=random(array);
			mean[0]=array[m];
			System.out.println(m+","+array[m][0]+","+array[m][1]+","+mean[0][0]+","+mean[0][1]);
			repeat:
			for(int j=1;j<k;j++){
					sum=0;
			for(int i=0;i<array_size;i++){
				if(j>1)
				{
					dis[0]=Euclidean(mean[j-2][0],array[i][0],mean[j-2][1],array[i][1]);
					dis[1]=Euclidean(mean[j-1][0],array[i][0],mean[j-1][1],array[i][1]);
					dis_index=getMinLocation(dis);
					d[i]=dis[dis_index];
				}else
				d[i]=Euclidean(mean[j-1][0],array[i][0],mean[j-1][1],array[i][1]);
				

				d2[i]=d[i]*d[i];
				sum=sum+d2[i];
			}
			for(int i=0;i<array_size;i++){
				p[i]=(float)d2[i]/sum;
			}
			int point=getMaxValue(p);
			mean[j]=array[point];
			System.out.println(point+","+array[point][0]+","+array[point][1]+","+mean[j][0]+","+mean[j][1]);
			if(mean[0][0]==mean[j][0] && mean[0][1]==mean[j][1])
				continue repeat;
				}
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
	public int random(int[][] a)
	{
		Random random = new Random(); 
		int value = random.nextInt(a.length);
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
	public static int getMaxValue(float[] array) {
	    int maxValue = (int) array[0];
	    		int location=0;
	    for (int i = 0; i < array.length; i++) {
	        if (array[i] > maxValue) {
	            maxValue = (int) array[i];
	            location=i;
	        }
	    }
	    return location;
	}
		}
