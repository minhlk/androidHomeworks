package course.labs.asynctasklab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;

public class DownloaderTaskFragment extends Fragment {

	private DownloadFinishedListener mCallback;
	private Context mContext;
//	private Button btn;
	@SuppressWarnings ("unused")
	private static final String TAG = "Lab-Threads";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Preserve across reconfigurations
		setRetainInstance(true);
		
		// TODO: Create new DownloaderTask that "downloads" data
		DownloaderTask downloaderTask = new DownloaderTask();
		
		// TODO: Retrieve arguments from DownloaderTaskFragment
		// Prepare them for use with DownloaderTask. 
        Bundle bd= this.getArguments();
        ArrayList<Integer> list = bd.getIntegerArrayList("friends");
        
        
        
		// TODO: Start the DownloaderTask 
        downloaderTask.execute(list);
        

	}

	// Assign current hosting Activity to mCallback
	// Store application context for use by downloadTweets()
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mContext = activity.getApplicationContext(); 

		// Make sure that the hosting activity has implemented
		// the correct callback interface.
		try {
			mCallback = (DownloadFinishedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement DownloadFinishedListener");
		}
	}

	// Null out mCallback
	@Override
	public void onDetach() {
		super.onDetach();
		mCallback = null;
	}

	// TODO: Implement an AsyncTask subclass called DownLoaderTask.
	// This class must use the downloadTweets method (currently commented
	// out). Ultimately, it must also pass newly available data back to 
	// the hosting Activity using the DownloadFinishedListener interface.

	public class DownloaderTask extends AsyncTask<ArrayList<Integer>,Void,String[]>

	{


		// TODO: Uncomment this helper method
		// Simulates downloading Twitter data from the network


		private String[] downloadTweets (Integer resourceIDS[]){
		final int simulatedDelay = 2000;
		String[] feeds = new String[resourceIDS.length];
		try {
			for (int idx = 0; idx < resourceIDS.length; idx++) {
				InputStream inputStream;
				BufferedReader in;
				try {
					// Pretend downloading takes a long time
					Thread.sleep(simulatedDelay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				inputStream = mContext.getResources().openRawResource(
						resourceIDS[idx]);
				in = new BufferedReader(new InputStreamReader(inputStream));

				String readLine;
				StringBuffer buf = new StringBuffer();

				while ((readLine = in.readLine()) != null) {
					buf.append(readLine);
				}

				feeds[idx] = buf.toString();

				if (null != in) {
					in.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return feeds;
	}


		@Override
		protected String[] doInBackground(ArrayList<Integer>... arrayLists) {
            Integer resourceIDS[] = new Integer[arrayLists[0].size()];
            for(int i = 0; i< arrayLists[0].size(); i++ ){
                resourceIDS[i] = arrayLists[0].get(i);

            }

			return downloadTweets(resourceIDS);
		}

        @Override
        protected void onPostExecute(String[] strings) {
            mCallback.notifyDataRefreshed(strings);
        }
    }
    
    

}