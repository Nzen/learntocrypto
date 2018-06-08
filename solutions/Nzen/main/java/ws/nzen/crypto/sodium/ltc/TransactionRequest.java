/** see ../../../../../../../LICENSE for release rights */
package ws.nzen.crypto.sodium.ltc;

/** Struct, likely to change
 */
public class TransactionRequest
{
	private RequestType cmd;
	private int amount;

	public RequestType getNature()
	{
		return cmd;
	}
	public void setNature( RequestType nature )
	{
		this.cmd = nature;
	}
	public String getCmd()
	{
		return cmd.getCmd();
	}
	public void setCmd( String json )
	{
		this.cmd = RequestType.fromJson( json );
	}

	public int getAmount()
	{
		return amount;
	}
	public void setAmount( int magnitude )
	{
		this.amount = magnitude;
	}

}




















