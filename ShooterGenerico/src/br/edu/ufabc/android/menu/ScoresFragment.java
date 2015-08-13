package br.edu.ufabc.android.menu;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import br.edu.ufabc.android.shootergenerico.R;

public class ScoresFragment extends Fragment {
	//private ListView lView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		if(container == null)
			return null;
		Log.e("sda", ""+R.id.listScores);
		//lView = (ListView) getView().findViewById(R.id.listScores);
		/*if(checkConnection()){
			
		}*/
		
		return inflater.inflate(R.layout.fragment_scores, container, false);	
	}
	
	private boolean checkConnection(){
		ConnectivityManager con = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		
		if (con != null)
			return true;
					
		return false;
	}

}

class JSONAsyncDownload extends AsyncTask<String, Void, Boolean>{
	private static final String _LOCAL = "http://localhost:3881";
	private static final String _AZURE = "http://scorepdmgame.azurewebsites.net/api/";
	private JSONObject jsono;
	
	@Override
	protected void onPreExecute(){
		
	}

	@Override
	protected Boolean doInBackground(String... params) {
		   try {
		        HttpGet httppost = new HttpGet(_LOCAL);
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpResponse response = httpclient.execute(httppost);

		        int status = response.getStatusLine().getStatusCode();

		        if (status == 200) {
		            HttpEntity entity = response.getEntity();
		            String data = EntityUtils.toString(entity);


		            jsono = new JSONObject(data);

		            return true;
		        }


		    } catch (IOException e) {
		        e.printStackTrace();
		    } catch (JSONException e) {

		        e.printStackTrace();
		    }
		    return false;
	}
	
	protected void onPostExecute(boolean status){
		if (status == true){
			
		}
	}
	
}
