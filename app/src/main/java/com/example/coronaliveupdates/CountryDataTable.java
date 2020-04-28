package com.example.coronaliveupdates;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CountryDataTable {
	TableLayout tableLayout;
	public CountryDataTable(TableLayout tableLayout){
		this.tableLayout=tableLayout;
	}
	public CountryDataTable(Context context){
		this.tableLayout=new TableLayout(context);
	}
	@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
	void addTableHeaderContents(String [] stringArray, int stringArrayLength){
		//making the table row
		TableRow tableRow=new TableRow(tableLayout.getContext());
		tableRow.setOrientation(LinearLayout.HORIZONTAL);
		//tableRow.setPadding(10,10,10,10);

		//making the textview array
		TextView[] textView=new TextView[stringArrayLength];

		//setting each of the strings in textviews
		for(int i=0;i<stringArrayLength;i++){
			textView[i]=new TextView(tableLayout.getContext());
			textView[i].setText(stringArray[i]);
			textView[i].setTextColor(Color.WHITE);
			textView[i].setPadding(15,10,15,10);

			textView[i].setAllCaps(true);
			textView[i].setBackgroundColor(Color.BLACK);
			textView[i].setTextSize(15);

		}
		//adding the textviews to the tablerow
		for(int i=0;i<stringArrayLength;i++){
			tableRow.addView(textView[i]);
		}
		//finally adding the table row to the table layout
		tableLayout.addView(tableRow);
	}



	@SuppressLint("NewApi")
	void addStringArrayContents(String [] stringArray, int stringArrayLength){
		//making the table row
		TableRow tableRow=new TableRow(tableLayout.getContext());
		tableRow.setOrientation(LinearLayout.HORIZONTAL);
		tableRow.setPadding(1,1,1,1);
		//making the textview array
		TextView[] textView=new TextView[stringArrayLength];

		//setting each of the strings in textviews
		for(int i=0;i<stringArrayLength;i++){
			textView[i]=new TextView(tableLayout.getContext());
			textView[i].setText(stringArray[i]);
			textView[i].setTextColor(Color.BLACK);
			textView[i].setPadding(10,10,10,10);
			if(i == 0)
				textView[i].setBackgroundColor(Color.LTGRAY);
			else if(i == 4){
				textView[i].setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
				textView[i].setBackgroundColor(Color.RED);
				textView[i].setTextColor(Color.WHITE);
			}
			else{
				textView[i].setBackgroundColor(Color.WHITE);
				textView[i].setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
			}

		}
		//adding the textviews to the tablerow
		for(int i=0;i<stringArrayLength;i++){
			tableRow.addView(textView[i]);
		}
		//finally adding the table row to the table layout
		tableLayout.addView(tableRow);
	}

}
