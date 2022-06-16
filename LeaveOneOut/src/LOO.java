
import java.io.*;
import java.util.*;

public class LOO {
	public static void main(String[] artgs)
	{
		int totalitems = 0;
		int canEst = 0;
		int cannotEst = 0;
		double newRatEst=0;
		double RMS=0;
		double RMSSum=0;
		double sum = 0;
		Map<String, List<UserInfo>> itemMap = new HashMap<String, List<UserInfo>>();
		System.out.println("Enter the path to where the ratings csv file is located:");
		Scanner keyboard = new Scanner(System.in);
		String ratings = keyboard.nextLine();
		BufferedReader br = null;
		String line = "";
		try {

			br = new BufferedReader(new FileReader(ratings));
			while ((line = br.readLine()) != null) 
			{
				String[] info = line.split(",");
				int u, i, r;
				u = Integer.parseInt(info[0]);
				i = Integer.parseInt(info[1]);
	            r = Integer.parseInt(info[2]);
	           
				UserInfo user = new UserInfo(u, r);
				List<UserInfo> ul = itemMap.get(info[1]);
				if(ul==null)
				{
					itemMap.put(info[1], ul=new ArrayList<UserInfo>());
					totalitems ++;
				}
				ul.add(user);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Enter name and path for new csv file to be created: ");
		String newFileName = keyboard.nextLine();
		try
		{
		FileWriter writer = new FileWriter(newFileName);
	
		for(Map.Entry<String, List<UserInfo>> entry : itemMap.entrySet())
		{
			List<UserInfo> ur = entry.getValue();
			int size = ur.size();
			int[] usNum = new int[size];
			int[] usRat = new int[size];
			for(int j = 0; j < size; j ++)
			{
				usNum[j] = ur.get(j).getNumber();
				usRat[j] = ur.get(j).getRating();
			}
			if(size == 1)
			{
				cannotEst ++;
				writer.write(usNum[0]+','+entry.getKey()+','+usRat[0]+','+"null"+','+"null");
				writer.write("\r\n");
			}
			else
			{
			for(int i=0; i < size; i ++)
			{
				sum = 0;
				for(int k = 0; k<size; k++)
				{
					if(k!=i)
					{
						sum += usRat[k];
					}	
				}
				newRatEst = sum/(size-1);
				RMS = Math.sqrt(Math.pow(newRatEst-usRat[i],2));
				RMSSum += RMS;
				writer.write(usNum[i]+','+entry.getKey()+','+usRat[i]+','+newRatEst+','+RMS);
				writer.write("\r\n");
				canEst ++;
			}
			}
		
			
		}
		writer.flush();
		writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Number that cannot be estimated: "+cannotEst);
		System.out.println("Number that can be estimated: "+canEst);
		System.out.println("Average RMS: " + RMSSum/canEst);
	} 
}



