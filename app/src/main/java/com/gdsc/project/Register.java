package com.gdsc.project;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;

public class Register extends AppCompatActivity {

    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private HashMap<String, Object> map = new HashMap<>();

    private boolean isPasswordVisible = false;

    private String designation = "";
    private double tag = 0;

    private LinearLayout linear2;
    private LinearLayout linear3;
    private LinearLayout linear4;
    private TextView textview1;
    private LinearLayout linear5;
    private LinearLayout linear12;
    private LinearLayout linear6;
    private LinearLayout linear9;
    private LinearLayout linear10;
    private LinearLayout genderlayout;
    private LinearLayout insurancelayout;
    private LinearLayout odlayout;
    private LinearLayout bglayout;
    private ScrollView vscroll1;
    private EditText name;
    private ImageView view;
    private EditText email;
    private EditText pass;
    private EditText age;
    private TextView gender;
    private EditText city;
    private EditText district;
    private EditText state;
    private EditText cn;
    private TextView od;
    private TextView bg;
    private EditText c1;
    private EditText c2;
    private EditText c3;
    private TextView insurance;
    private Button button1;

    private ProgressBar progressbar1;

    private AlertDialog.Builder d;
    private Intent INTENT = new Intent();

    private FirebaseAuth fbAUTH;
    private OnCompleteListener<AuthResult> _fbAUTH_create_user_listener;

    private DatabaseReference user = _firebase.getReference("user");
    private ChildEventListener _user_child_listener;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialize(_savedInstanceState);
        FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);
        linear5 = findViewById(R.id.linear5);
        linear6 = findViewById(R.id.linear6);
        linear9 = findViewById(R.id.linear9);
        linear10 = findViewById(R.id.linear10);
        name = findViewById(R.id.name);
        vscroll1 = findViewById(R.id.vscroll1);
        view = findViewById(R.id.view);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        button1 = findViewById(R.id.button1);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        genderlayout = findViewById(R.id.genderlayout);
        insurancelayout = findViewById(R.id.insurancelayout);
        odlayout = findViewById(R.id.odlayout);
        bglayout = findViewById(R.id.bglayout);
        cn = findViewById(R.id.cn);
        od = findViewById(R.id.od);
        bg = findViewById(R.id.bg);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        district = findViewById(R.id.district);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        insurance = findViewById(R.id.insurance);
        progressbar1 = findViewById(R.id.progressbar1);
        fbAUTH = FirebaseAuth.getInstance();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                int cursorPosition = pass.getSelectionStart();
                if (isPasswordVisible) {
                    //show

                    pass.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance());
                    isPasswordVisible = false;
                    view.setImageResource(R.drawable.hide);
                }
                else {
                    //hide password

                    pass.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
                    isPasswordVisible = true;
                    view.setImageResource(R.drawable.show);
                }
                pass.setSelection(cursorPosition);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
                if (email.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter your email address...", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.getText().toString().trim().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please enter your password...", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (name.getText().toString().trim().equals("")) {
                            Toast.makeText(getApplicationContext(), "Please enter your name...", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (age.getText().toString().trim().equals("")) {
                                Toast.makeText(getApplicationContext(), "Please enter your age...", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if (cn.getText().toString().trim().equals("")||cn.getText().toString().trim().length()<10||cn.getText().toString().trim().length()>15) {
                                    Toast.makeText(getApplicationContext(), "Please enter valid contact number...", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    if (city.getText().toString().trim().equals("")) {
                                        Toast.makeText(getApplicationContext(), "Please enter your city...", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        if (district.getText().toString().trim().equals("")) {
                                            Toast.makeText(getApplicationContext(), "Please enter your district...", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            if (state.getText().toString().trim().equals("")) {
                                                Toast.makeText(getApplicationContext(), "Please enter your state...", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                if (insurance.getText().toString().trim().equals("")||insurance.getText().toString().trim().equals("Choose an option")) {
                                                    Toast.makeText(getApplicationContext(), "Please select your insurance info...", Toast.LENGTH_SHORT).show();
                                                }
                                                else {
                                                    if (od.getText().toString().trim().equals("")||od.getText().toString().trim().equals("Choose an option")) {
                                                        Toast.makeText(getApplicationContext(), "Please select your organ donation info...", Toast.LENGTH_SHORT).show();
                                                    }
                                                    else {
                                                        if (c1.getText().toString().trim().equals("")||c1.getText().toString().trim().length()<10||c1.getText().toString().trim().length()>15) {
                                                            Toast.makeText(getApplicationContext(), "Please enter valid contact 1...", Toast.LENGTH_SHORT).show();
                                                        }
                                                        else {
                                                            if (c2.getText().toString().trim().equals("")||c2.getText().toString().trim().length()<10||c2.getText().toString().trim().length()>15) {
                                                                Toast.makeText(getApplicationContext(), "Please enter valid contact 2...", Toast.LENGTH_SHORT).show();
                                                            }
                                                            else {
                                                                if (c3.getText().toString().trim().equals("")||c3.getText().toString().trim().length()<10||c3.getText().toString().trim().length()>15) {
                                                                    Toast.makeText(getApplicationContext(), "Please enter valid contact 3...", Toast.LENGTH_SHORT).show();
                                                                }
                                                                else {
                                                                    if (gender.getText().toString().trim().equals("")||gender.getText().toString().trim().equals("Choose your gender")) {
                                                                        Toast.makeText(getApplicationContext(), "Please select your gender...", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                    else {
                                                                        if (bg.getText().toString().trim().equals("")||bg.getText().toString().trim().equals("Choose your blood group")) {
                                                                            Toast.makeText(getApplicationContext(), "Please select your blood group...", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                        else {
                                                                            progressbar1.setVisibility(View.VISIBLE);
                                                                            fbAUTH.createUserWithEmailAndPassword(email.getText().toString().trim(), pass.getText().toString().trim()).addOnCompleteListener(Register.this, _fbAUTH_create_user_listener);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
			}
		});

        genderlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popu = new PopupMenu(Register.this, genderlayout);
                Menu genderlayout = popu.getMenu();
                genderlayout.add("Male");
                genderlayout.add("Female");
                genderlayout.add("Rather not say");
                popu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString()) {
                            case "Male":
                                gender.setText("Male");
                                return true;
                            case "Female":
                                gender.setText("Female");
                                return true;
                            case "Rather not say":
                                gender.setText("Rather not say");
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                try {
                    java.lang.reflect.Field[] fields = popu.getClass().getDeclaredFields();
                    for (java.lang.reflect.Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(popu); Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                            java.lang.reflect.Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class); setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
                popu.show();
            }
        });

        bglayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popu = new PopupMenu(Register.this, bglayout);
                Menu bglayout = popu.getMenu();
                bglayout.add("A+");
                bglayout.add("A-");
                bglayout.add("B+");
                bglayout.add("B-");
                bglayout.add("O+");
                bglayout.add("O-");
                bglayout.add("AB+");
                bglayout.add("AB-");
                popu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString()) {
                            case "A+":
                                bg.setText("A+");
                                return true;
                            case "A-":
                                bg.setText("A-");
                                return true;
                            case "B+":
                                bg.setText("B+");
                                return true;
                            case "B-":
                                bg.setText("B-");
                                return true;
                            case "O+":
                                bg.setText("O+");
                                return true;
                            case "O-":
                                bg.setText("O-");
                                return true;
                            case "AB+":
                                bg.setText("AB+");
                                return true;
                            case "AB-":
                                bg.setText("AB-");
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                try {
                    java.lang.reflect.Field[] fields = popu.getClass().getDeclaredFields();
                    for (java.lang.reflect.Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(popu); Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                            java.lang.reflect.Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class); setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
                popu.show();
            }
        });

        insurancelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popu = new PopupMenu(Register.this, insurancelayout);
                Menu insurancelayout = popu.getMenu();
                insurancelayout.add("Yes");
                insurancelayout.add("No");
                popu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString()) {
                            case "Yes":
                                insurance.setText("Yes");
                                return true;
                            case "No":
                                insurance.setText("No");
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                try {
                    java.lang.reflect.Field[] fields = popu.getClass().getDeclaredFields();
                    for (java.lang.reflect.Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(popu); Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                            java.lang.reflect.Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class); setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
                popu.show();
            }
        });

        odlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popu = new PopupMenu(Register.this, odlayout);
                Menu odlayout = popu.getMenu();
                odlayout.add("Yes");
                odlayout.add("No");
                popu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString()) {
                            case "Yes":
                                od.setText("Yes");
                                return true;
                            case "No":
                                od.setText("No");
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                try {
                    java.lang.reflect.Field[] fields = popu.getClass().getDeclaredFields();
                    for (java.lang.reflect.Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(popu); Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                            java.lang.reflect.Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class); setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
                popu.show();
            }
        });

        _fbAUTH_create_user_listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> _param1) {
                final boolean _success = _param1.isSuccessful();
                final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
                if (_success) {
                    FirebaseAuth auth = FirebaseAuth.getInstance(); com.google.firebase.auth.FirebaseUser users = auth.getCurrentUser(); users.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() { @Override public void onComplete(Task task) { if (task.isSuccessful()) {
                        map = new HashMap<>();
                        map.put("name", name.getText().toString().trim());
                        map.put("age", age.getText().toString().trim());
                        map.put("bg", bg.getText().toString().trim());
                        map.put("gender", gender.getText().toString().trim());
                        map.put("cn", cn.getText().toString().trim());
                        map.put("city", city.getText().toString().trim());
                        map.put("district", district.getText().toString().trim());
                        map.put("state", state.getText().toString().trim());
                        map.put("insurance", insurance.getText().toString().trim());
                        map.put("od", od.getText().toString().trim());
                        map.put("c1", c1.getText().toString().trim());
                        map.put("c2", c2.getText().toString().trim());
                        map.put("c3", c3.getText().toString().trim());
                        map.put("email", email.getText().toString().trim());
                        user.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
                        map.clear();
                        final AlertDialog dialog1 = new AlertDialog.Builder(Register.this).create();
                        View inflate = getLayoutInflater().inflate(R.layout.verifyemail,null);
                        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        dialog1.setView(inflate);
                        TextView t1x = (TextView) inflate.findViewById(R.id.t1x);

                        TextView t2x = (TextView) inflate.findViewById(R.id.t2x);

                        TextView b1x = (TextView) inflate.findViewById(R.id.b1x);

                        ImageView i1x = (ImageView) inflate.findViewById(R.id.i1x);

                        LinearLayout bgx = (LinearLayout) inflate.findViewById(R.id.bgx);
                        i1x.setImageResource(R.drawable.verification);
                        t1x.setText("Account Verification");
                        t2x.setText("A verification mail has been sent to your account. Verify your email address and login.");
                        b1x.setText("Okay");
                        _rippleRoundStroke(bgx, "#FFFFFF", "#000000", 15, 0, "#000000");
                        _rippleRoundStroke(b1x, "#E23744", "#f5f5f5", 100, 2, "#eeeeee");
                        b1x.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
                            dialog1.dismiss();
                            INTENT.setClass(getApplicationContext(), Login.class);
                            INTENT.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(INTENT);
                            finish();
                        }
                        });
                        dialog1.setCancelable(false);
                        dialog1.show();
                    } else {
                        progressbar1.setVisibility(View.GONE);
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(getApplicationContext(), "Please verify your email address.",  Toast.LENGTH_SHORT).show();
                    } }});

                }
                else {
                    progressbar1.setVisibility(View.GONE);
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(getApplicationContext(), _errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        };

        _user_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onChildChanged(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onChildMoved(DataSnapshot _param1, String _param2) {

            }

            @Override
            public void onChildRemoved(DataSnapshot _param1) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onCancelled(DatabaseError _param1) {
                final int _errorCode = _param1.getCode();
                final String _errorMessage = _param1.getMessage();

            }
        };
        user.addChildEventListener(_user_child_listener);
    }

    private void initializeLogic() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(0xFFE7EEFB);
        Window window = this.getWindow();
        window.setNavigationBarColor(Color.parseColor("#E23744"));
        vscroll1.setVerticalScrollBarEnabled(false);
        _rippleRoundStroke(button1, "#E23744", "#f5f5f5", 100, 2, "#eeeeee");
        designation = "";
        progressbar1.setVisibility(View.GONE);
        name.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        email.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        pass.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        age.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        bg.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        gender.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        cn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        city.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        district.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        state.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        insurance.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        od.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        c1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        c2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        c3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; }}.getIns((int)50, (int)0, 0xFFFFFFFF, 0xFFEEEEEE));
        isPasswordVisible = true;
    }

    public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
        GradientDrawable GG = new GradientDrawable();
        GG.setColor(Color.parseColor(_focus));
        GG.setCornerRadius((float)_round);
        GG.setStroke((int) _stroke,
                Color.parseColor("#" + _strokeclr.replace("#", "")));
        android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
        _view.setBackground(RE);
    }
}
