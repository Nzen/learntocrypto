/** see ../../../../../../../LICENSE for release rights */
package ws.nzen.crypto.sodium.ltc;

public enum RequestType
{
	BALANCE_AMOUNT( "balance" ),
	DEPOSIT( "deposit" ),
	WITHDRAWL( "withdraw" ),
	REJECT( "rejected" ),
	UNRECOGNIZED( "bamf" );

	private String commandId;

	private RequestType( String forJson )
	{
		if ( forJson != null )
		{
			commandId = forJson;
		}
		else
		{
			commandId = "";
		}
	}

	public String getCmd()
	{
		return commandId;
	}

	public static RequestType fromJson( String type )
	{
		if ( type == null || type.isEmpty() )
		{
			return RequestType.UNRECOGNIZED;
		}
		else
		{
			for ( RequestType candidate : RequestType.values() )
			{
				if ( type.equals( candidate.getCmd() ) )
				{
					return candidate;
				}
			}
			// else
			return RequestType.UNRECOGNIZED;
		}
	}

}




















