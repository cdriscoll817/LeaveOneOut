public class UserInfo {
	
	public int userNumber;
	public int userItemRating;
	public double estimatedRating;
	public boolean estimate;
	
	public UserInfo(int n, int r)
	{
		userNumber = n;
		userItemRating = r;
	}
	
	public int getNumber()
	{
		return userNumber;
	}
	
	public int getRating()
	{
		return userItemRating;
	}
	
	public boolean getEstimate()
	{
		return estimate;
	}
	
	public double getEstimatedRating()
	{
		return estimatedRating;
	}
	
	public void setEstimate(boolean b)
	{
		estimate = b;
	}
	
	public void setEstimatedRating(double er)
	{
		estimatedRating = er;
	}
	
	public void setNumber(int n)
	{
		userNumber = n;
	}
	
	public void setRating(int r)
	{
		userItemRating = r;
	} 
		
}
