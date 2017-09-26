package com.tutrieuchau.winwin.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tutrieuchau.winwin.Adapter.MissionAdapter;
import com.tutrieuchau.winwin.Model.Mission;
import com.tutrieuchau.winwin.R;
import com.tutrieuchau.winwin.Utils.Utils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MissionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MissionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MissionFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ArrayList<Mission> missionArrayList;
    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);
    }

    private OnFragmentInteractionListener mListener;

    public MissionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MissionFragment newInstance(String param1, String param2) {
        MissionFragment fragment = new MissionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mission, container, false);
        ListView listView = (ListView)view.findViewById(R.id.missionListView);
        //data
        ArrayList<Mission.Alarm> alarms = new ArrayList<>();
        Mission.Alarm alarm = new Mission.Alarm(true,360);
        alarms.add(alarm);
        alarms.add(alarm);
        alarms.add(alarm);
        alarms.add(alarm);
        alarms.add(alarm);
        alarms.add(alarm);
        alarms.add(alarm);
        ArrayList<Mission.Progress> progresses = new ArrayList<>();
        Mission.Progress progress = new Mission.Progress("Curabitur",40,24);
        progresses.add(progress);
        progresses.add(progress);
        progresses.add(progress);
        missionArrayList = new ArrayList<>();
        Mission mission = new Mission("In in velit quis arcu ornare","Nunc at velit quis lectus nonummy eleifend. Curabitur eros. Aenean ligula dolor, gravida auctor, auctor et, suscipit in, erat. Sed malesuada, enim ut congue pharetra, massa elit convallis pede",
                " Curabitur eros",30,alarms, Utils.DEFAULT_ICON.LEARNING,progresses);
        missionArrayList.add(mission);
        MissionAdapter adapter = new MissionAdapter(this.getContext(),missionArrayList);
        listView.setAdapter(adapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
