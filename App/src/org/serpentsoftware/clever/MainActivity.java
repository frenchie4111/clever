package org.serpentsoftware.clever;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); 
			StrictMode.setThreadPolicy(policy); 
			
			ConnectionConfiguration config = new ConnectionConfiguration("10.0.0.16", 5223, "clever");
			XMPPConnection conn = new XMPPConnection(config);
			try {
				Log.v("conn", "About to conn");
				conn.connect();
				Log.v("conn", "conn");
				conn.login("android", "notapassword", "");
				
				ChatManager chatmanager = conn.getChatManager();
				final Chat newChat = chatmanager.createChat("main@clever", new MessageListener() {
					// Receiving Messages
					public void processMessage(Chat chat, Message message) {
						Message outMsg = new Message(message.getBody());
						try {
							//Send Message object
							chat.sendMessage(outMsg);
						} catch (XMPPException e) {
							//Error
						}
					}
				});
				
				try {
					newChat.sendMessage("Hello There");
				} catch( XMPPException e ) {
					e.printStackTrace();
				}
				
				Log.v("this", "Is Connected? " + conn.isConnected() );
			} catch (XMPPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return rootView;
		}
	}

}
