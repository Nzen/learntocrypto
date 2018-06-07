/** see ../../../../../../../LICENSE for release rights */
package ws.nzen.crypto.sodium.ltc;

import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;

/** 
 */
public class Teller
{
	private static final String cl = "t.";
	private int tcpPort = 3876;
	private Socket mouth;


	public Teller()
	{
	}


	public void request()
	{
		final String here = cl +"r ";
		String localhost = "127.0.0.1";
		try
		(
				Socket mouth = new Socket( localhost, tcpPort );
				java.io.PrintWriter netOut = new java.io.PrintWriter(
						mouth.getOutputStream(), true );
				java.io.BufferedReader netIn = new java.io.BufferedReader(
						new java.io.InputStreamReader( mouth.getInputStream() ) );
		)
		{
			String inputT, outputT;
			int times = 0;
			// Improve use a real protocol
			Map<String,String> request = new TreeMap<>();
			request.put("cmd", "balance");
			netOut.println( JSON.toJSONString(request) );
			times++;
			while ( (inputT = netIn.readLine()) != null ) // IMPROVE assignment as expression
			{
				System.out.println( here +"Teller received: "+ inputT );
				outputT = inputT;// referee.replyFrom( inputT );
				if ( times > 0 || outputT.equals( "END" ) )
				{
					break;
				}
				else
				{
					netOut.println( outputT ); // echo
					times++;
				}
			}
		}
		catch ( java.io.IOException | SecurityException | java.nio.channels.IllegalBlockingModeException ie )
		{
			System.err.println( "couldn't accept a connection "+ ie );
		}
	}

}




















