package com.waterlooeventfinder2.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

public class AsyncDataProviderExample {

	/**
	 * A custom {@link AsyncDataProvider}.
	 */
	public static class MyDataProvider extends AsyncDataProvider<String> {
		/**
		 * {@link #onRangeChanged(HasData)} is called when the table requests a
		 * new range of data. You can push data back to the displays using
		 * {@link #updateRowData(int, List)}.
		 */
		@Override
		protected void onRangeChanged(HasData<String> display) {
			// Get the new range.
			
			final Range range = display.getVisibleRange();
			
			/*
			 * Query the data asynchronously. If you are using a database, you
			 * can make an RPC call here.
			 */
			
					// We are creating fake data. Normally, the data will come
					// from a
					// server.
					int start = range.getStart();
					int length = range.getLength();
					
					//List<String> newData = new ArrayList<String>();
					
					List<String> newData = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
							"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26");
					
					/*for (int i = start; i < start + 10; i++) {
						newData.add("Item " + i + " " + range.getLength());
					}*/

					// Push the data to the displays. AsyncDataProvider will
					// only update
					// displays that are within range of the data.
					
					updateRowData(start, newData);
				
		}
	}
}
