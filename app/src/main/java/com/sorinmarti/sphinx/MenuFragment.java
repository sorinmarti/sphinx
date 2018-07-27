package com.sorinmarti.sphinx;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnMenuFragmentInteraction} interface
 * to handle interaction events.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
    private static final String ARG_SHOW_BACK = "show back option";
    private static final String ARG_SHOW_EXIT = "show exit option";
    private static final String ARG_SHOW_MENU = "show menu option";

    private ImageButton btnBack, btnExit, btnMenu;
    private boolean showBack, showExit, showMenu;

   private OnMenuFragmentInteraction mListener;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment MenuFragment.
     */
    public static MenuFragment newInstance(boolean showBack, boolean showExit, boolean showMenu) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_SHOW_BACK, showBack);
        args.putBoolean(ARG_SHOW_EXIT, showExit);
        args.putBoolean(ARG_SHOW_MENU, showMenu);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            showBack = getArguments().getBoolean(ARG_SHOW_BACK);
            showExit = getArguments().getBoolean(ARG_SHOW_EXIT);
            showMenu = getArguments().getBoolean(ARG_SHOW_MENU);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        btnBack = (ImageButton) view.findViewById(R.id.fragmentMenuBack);
        btnExit = (ImageButton) view.findViewById(R.id.fragmentMenuExit);
        btnMenu = (ImageButton) view.findViewById(R.id.fragmentMenuMenu);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSphinxBackPressed();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSphinxExitPressed();
            }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSphinxMenuPressed();
            }
        });

        if (showBack) {
            btnBack.setVisibility(View.VISIBLE);
        } else {
            btnBack.setVisibility(View.INVISIBLE);
        }
        if (showExit) {
            btnExit.setVisibility(View.VISIBLE);
        } else {
            btnExit.setVisibility(View.INVISIBLE);
        }
        if (showMenu) {
            btnMenu.setVisibility(View.VISIBLE);
        } else {
            btnMenu.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMenuFragmentInteraction) {
            mListener = (OnMenuFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnMenuFragmentInteraction");
        }
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
    public interface OnMenuFragmentInteraction {
        void onSphinxBackPressed();
        void onSphinxExitPressed();
        void onSphinxMenuPressed();
    }
}
