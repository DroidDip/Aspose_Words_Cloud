package com.aspose.cloud.sdk.appdemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aspose.cloud.appdemo.R;

public class BrowseFileActivity extends Activity {
	private ListView browseList;
	private List<String> item = null;
	private List<String> path = null;
	private String root = "/";
	private TextView myPath;
	private String absolutePath = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse_file_layout);
		myPath = (TextView) findViewById(R.id.path);
		browseList = (ListView) findViewById(R.id.browse_list);
		getDir(root);

	}

	private void getDir(String dirPath) {
		// TODO Auto-generated method stub
		myPath.setText("Location: " + dirPath);
		item = new ArrayList<String>();
		path = new ArrayList<String>();
		File f = new File(dirPath);
		File[] files = f.listFiles();
		if (!dirPath.equals(root)) {
			item.add(root);
			path.add(root);
			item.add("../");
			path.add(f.getParent());
		}
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			path.add(file.getPath());
			if (file.isDirectory())
				item.add(file.getName() + "/");
			else
				item.add(file.getName());
		}
		ArrayAdapter<String> fileList = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, item);
		browseList.setAdapter(fileList);
		browseList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						final File file = new File(path.get(position));
						if (file.isDirectory()) {
							if (file.canRead())
								getDir(path.get(position));
							else {
								new AlertDialog.Builder(BrowseFileActivity.this)
										.setTitle(
												"'"
														+ file.getName()
														+ "' folder can't be read!")
										.setPositiveButton(
												"OK",
												new DialogInterface.OnClickListener() {
													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														// TODO Auto-generated
														// method stub
													}
												}).show();
							}
						} else {
							new AlertDialog.Builder(BrowseFileActivity.this)
									.setTitle(
											"Select '" + file.getName() + "' ?")
									.setPositiveButton(
											"OK",
											new DialogInterface.OnClickListener() {
												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													absolutePath = file
															.getAbsolutePath();

													Intent resultIntent = new Intent();
													resultIntent.putExtra(
															"path",
															absolutePath);
													setResult(RESULT_OK,
															resultIntent);
													finish();
												}
											})
									.setNegativeButton("Cancel", null).show();

						}
					}
				});
	}

}
