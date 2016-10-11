package com.luarca84.diego.luarca;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;

public class PuzzleActivity extends AppCompatActivity {

    /* globally declare this arraylist, this will be used to display your image chunks into the gridview*/
    ArrayList<Bitmap> bitmapStorageList;
    ArrayList<Integer> integers;
    int indexCurrentImage =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);


        NewGame();
    }

    public void onClickButtonNewImage(View view)
    {
        //Toast.makeText(getApplicationContext(),"New Image",Toast.LENGTH_SHORT).show();
        indexCurrentImage++;
        if(indexCurrentImage == 5)
            indexCurrentImage = 0;
        ImageView imageView = (ImageView)findViewById(R.id.imageview_main);
        switch (indexCurrentImage)
        {
            case 0: imageView.setImageResource(R.drawable.luarca);
                break;
            case 1: imageView.setImageResource(R.drawable.luarca2);
                break;
            case 2: imageView.setImageResource(R.drawable.luarca3);
                break;
            case 3: imageView.setImageResource(R.drawable.luarca4);
                break;
            case 4: imageView.setImageResource(R.drawable.luarca5);
                break;
        }
        //GridView gridView = (GridView)findViewById(R.id.gridview_main);
        //gridView.removeAllViews();
        NewGame();
    }

    private void NewGame()
    {
        ModelManager.getInstance().setNewGame(true);
        GridView gridView = (GridView)findViewById(R.id.gridview_main);
        ImageView imageView = (ImageView)findViewById(R.id.imageview_main);
        int rows = 2;
        int columns = 3;
        splitImage(imageView,rows,columns);
        integers = new ArrayList<>();
        for(int i =0; i<bitmapStorageList.size();i++)
        {
            Bitmap b = bitmapStorageList.get(i);
            int id = b.getGenerationId();
            integers.add(id);
        }

        gridView.setNumColumns(columns);

        Collections.shuffle(bitmapStorageList);
        CustomGrid adapter = new CustomGrid(PuzzleActivity.this, bitmapStorageList,integers);
        gridView.setAdapter(adapter);
    }



    private void splitImage(ImageView imageView, int rows, int columns) {
//For height and width of the small image chunks
        int chunkHeight, chunkWidth;
        Bitmap image; // use to store image chunks
        int chunkNumbers = rows * columns;
//To store all the small image chunks in bitmap format in this list
        bitmapStorageList = new ArrayList<Bitmap>(chunkNumbers);
//Getting the BitmapDrawable from the imageview where your image is displayed
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
//setting the height and width of each pieces according to the rows and columns
        chunkHeight = bitmap.getHeight() / rows;
        chunkWidth = bitmap.getWidth() / columns;
//x and y are the pixel positions of the image chunks
        int yCoord = 0;
        for (int i = 0; i < rows; i++) {
            int xCoord = 0;
            for (int j = 0; j < columns; j++) {
                image = Bitmap.createBitmap(scaledBitmap, xCoord, yCoord, chunkWidth, chunkHeight);
                bitmapStorageList.add(image);
                xCoord = xCoord + chunkWidth;
            }
            yCoord = yCoord + chunkHeight;
        }
/* Once, you are done with this split task then you can show your arraylist into the gridview */
    }
}
