package homework09stupid;

//This class is used to help keep track of the number of probes
//at specific parts in the table

public class SpecificProbeCounter {
	
	protected  int arrayIndex, thisProbeCount, timesVisted;
	protected SpecificProbeCounter[] arrayOfCounters;
	
	public SpecificProbeCounter(int arrayIndex)
	{
		this.arrayIndex = arrayIndex;
		thisProbeCount = 0;
		timesVisted = 0;
	}
	public void doStuff()
	{
		this.thisProbeCount++;
	}
	
	public double  findAverageProbes()
	{
		return thisProbeCount/(timesVisted*1.0);
	}

}
