package com.luarca84.diego.luarca;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by USUARIO on 11/10/2016.
 */
public class CustomGrid extends BaseAdapter {
    private Context mContext;
    ArrayList<Bitmap> bitmapStorageList;
    ArrayList<Integer> integers;

    public CustomGrid(Context c,ArrayList<Bitmap> lst ,ArrayList<Integer> integers) {
        mContext = c;
        this.bitmapStorageList = lst;
        this.integers = integers;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return bitmapStorageList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return bitmapStorageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //if (convertView == null) {

        grid = new View(mContext);
        grid = inflater.inflate(R.layout.grid_single, null);
        //TextView textView = (TextView) grid.findViewById(R.id.grid_text);
        ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
        imageView.setTag(position);
        //textView.setText(web[position]);
        //imageView.setImageResource(Imageid[position]);

        imageView.setImageBitmap(bitmapStorageList.get(position));
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Toast.makeText(mContext,"OnTouch",Toast.LENGTH_SHORT).show();

                //return false;
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if(ModelManager.getInstance().isNewGame())
                    {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                                view);
                        view.startDrag(data, shadowBuilder, view, 0);
                        //view.setVisibility(View.INVISIBLE);
                        return true;
                    }
                }
                else
                {
                    return false;
                }
                return false;
            }
        });


        grid.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {

                if(dragEvent.getAction() == DragEvent.ACTION_DROP)
                {
                    ImageView viewDrag = (ImageView) dragEvent.getLocalState();
                    int positionInitial = (int)viewDrag.getTag();
                    GridView owner = (GridView) view.getParent();
                    int positionFinal3 =((GridView)owner).getPositionForView(view);

                    Bitmap aux = bitmapStorageList.get(positionInitial);
                    bitmapStorageList.set(positionInitial, bitmapStorageList.get(positionFinal3));
                    bitmapStorageList.set(positionFinal3,aux);
                    notifyDataSetChanged();


                    //Toast.makeText(mContext,"ACTION_DROP "+positionInitial+" "+positionFinal+" "+positionFinal2+" "+positionFinal3,Toast.LENGTH_SHORT).show();
                    return true;
                }
                return true;
            }
        });

        //} else {
        //  grid = (View) convertView;
        //}

        if(CheckVictory() && ModelManager.getInstance().isNewGame())
        {
            ModelManager.getInstance().setNewGame(false);
            Toast.makeText(mContext,"Victoria",Toast.LENGTH_SHORT).show();
        }

        return grid;
    }

    private boolean CheckVictory()
    {
        for(int i = 0; i< integers.size();i++)
        {
            if(integers.get(i)!= bitmapStorageList.get(i).getGenerationId())
                return false;
        }

        return  true;
    }
}
