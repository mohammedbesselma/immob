package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammed.immob.HomeActivity;
import com.example.mohammed.immob.R;
import com.example.mohammed.immob.Reservation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import AsyncTask.BackTask;

/**
 * Created by Mohammed on 05/06/2018.
 */

public class ReservationListAdapter  extends BaseAdapter {

    private Context mContext;
    public static ArrayList<HashMap<String, String>> reservationlist;
    private static LayoutInflater inflater = null;
    String i;


    public ReservationListAdapter(Context context, ArrayList<HashMap<String, String>> data){

        mContext = context;
        reservationlist = data;
        this.i=i;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return reservationlist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;



        view = inflater.inflate(R.layout.reservationrow, null);

        TextView nomprenom = (TextView) view.findViewById(R.id.nomprenom);
        TextView date = (TextView) view.findViewById(R.id.datereservation);

        Button confirmer = (Button)view.findViewById(R.id.confirmer);
        Button annuler = (Button)view.findViewById(R.id.annuler);

        HashMap<String, String> reservation = new HashMap<>();

        reservation = reservationlist.get(position);



        nomprenom.setText(reservation.get("nom")+" "+reservation.get("prenom"));
        date.setText(reservation.get("date"));

        if (reservation.get("validation").equals("valide")){
            confirmer.setVisibility(View.GONE);
        }
        if (reservation.get("validation").equals("annule")){
            annuler.setVisibility(View.GONE);
        }


        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext,reservationlist.get(position).get("validation"),Toast.LENGTH_LONG).show();
                new BackTask(mContext).execute("validation",reservationlist.get(position).get("id_reservation"),"valide");
                new BackTask(mContext).execute("reservation", Reservation.idimmob);
            }
        });
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext,reservationlist.get(position).get("validation"),Toast.LENGTH_LONG).show();
                new BackTask(mContext).execute("validation",reservationlist.get(position).get("id_reservation"),"annule");
                new BackTask(mContext).execute("reservation", Reservation.idimmob);

            }
        });














        return view;
    }
}
