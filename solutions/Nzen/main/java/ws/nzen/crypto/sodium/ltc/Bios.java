/** see ../../../../../../../LICENSE for release rights */
package ws.nzen.crypto.sodium.ltc;

/** 
 */
public class Bios
{

	public static void main( String[] args )
	{
		// cli stuff, eventually
		if ( args.length == 0 || args[ 0 ].equals( "Bank" ) )
		{
			new Bank().serveClientele();
		}
		else if ( args[ 0 ].equals( "Client" ) )
		{
			new Teller().request();
		}
		else
		{
			System.out.println( "Sorry, this is naive to keep it quick"
					+ "\nuse nothing, Bank, or Client to do something. exiting" );
		}
	}

}




















