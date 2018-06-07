/** see ../../../../../../../LICENSE for release rights */
package ws.nzen.crypto.sodium.ltc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.IllegalBlockingModeException;

/** 
 */
public class Bank
{
	private static final String cl = "b.";
	private int tcpPort = 3876;
	private ServerSocket ear;

	/**
	 * @param args
	 */
	public static void main( String[] args )
	{
		// cli stuff, eventually
		new Bank().serveClientele();
	}


	public Bank()
	{
		try
		{
			ear = new ServerSocket( tcpPort );
		}
		catch ( java.io.IOException | SecurityException | java.nio.channels.IllegalBlockingModeException iff )
		{
			System.err.println( "couldn't accept a connection "+ iff );
		}
	}


	public void serveClientele()
	{
		final String here = cl +"e ";
		System.out.println( here +"listening for clients" );
		try ( Socket bidiChannel = ear.accept();
				PrintWriter netOut = new PrintWriter(
						bidiChannel.getOutputStream(), true );
				BufferedReader netIn = new BufferedReader(
						new InputStreamReader( bidiChannel.getInputStream() ) ); )
		{
			respond( netIn, netOut );
			// NOTE while respond will last a whole conversation, this doesn't stay alive after the first one
		}
		catch ( IOException | SecurityException | IllegalBlockingModeException ie )
		{
			System.err.println( here +"couldn't accept connections"+ ie );
		}
	}


	private void respond( BufferedReader netIn,
			PrintWriter netOut ) throws IOException
	{
		final String here = cl +"r ";
		String inputT, outputT;
		// AhpProtocol referee = new AhpProtocol();
		int times = 0;
		while ( (inputT = netIn.readLine()) != null ) // IMPROVE assignment as expression
		{
			System.out.println( here +"Bank received: "+ inputT );
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

}




















