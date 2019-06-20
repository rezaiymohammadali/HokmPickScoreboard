package com.example.mar.hokmpick.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mar.hokmpick.R;
import com.example.mar.hokmpick.classes.G;
import com.example.mar.hokmpick.classes.InternetHelper;
import com.example.mar.hokmpick.classes.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SecondActivity extends AppCompatActivity {

    Context context = SecondActivity.this;
    private Boolean exit = false;

    private Typeface TF;

    private int i = -1;

    TextView mem1;
    TextView mem2;
    TextView mem3;
    TextView mem4;
    TextView mem5;
    TextView mem6;
    TextView date;

    Button btn_OK;
    Button btn_add;
    EditText edt_called1;
    EditText edt_called2;
    EditText edt_catched1;
    EditText edt_catched2;
    RecyclerView recyclerView;

    private List<ScoreData> scoreList = new ArrayList<>();
    ScoreAdapter scoreAdapter;

    String member_1;
    String member_2;
    String member_3;
    String member_4;
    String member_5;
    String member_6;

    int sumScoreOne = 0;
    int sumScoreTwo = 0;
    int reg_called_one = 0;
    int reg_called_two = 0;
    int reg_catched_one = 0;
    int reg_catched_two = 0;
    public static boolean group_one_catched_7 = false;
    public static boolean group_two_catched_7 = false;

    private boolean received = false;
    private String w1 = "";
    private String w2 = "";
    private String w3 = "";
    private String l1 = "";
    private String l2 = "";
    private String l3 = "";
    private int w_score;
    private int l_score;
    private int statusResult = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TF = Utility.getTypeFace(this, Utility.FONT_TYPE_REGULAR);

        btn_OK = findViewById(R.id.btnOK);
        btn_add = findViewById(R.id.btnAdd);
        edt_called1 = findViewById(R.id.edt_called1);
        edt_called2 = findViewById(R.id.edt_called2);
        edt_catched1 = findViewById(R.id.edt_catched1);
        edt_catched2 = findViewById(R.id.edt_catched2);
        recyclerView = findViewById(R.id.recycler_view);

        btn_OK.setTypeface(TF);
        btn_add.setTypeface(TF);
        edt_called1.setTypeface(TF);
        edt_called2.setTypeface(TF);
        edt_catched1.setTypeface(TF);
        edt_catched2.setTypeface(TF);

        Utility.edtNumE2P(edt_called1);
        Utility.edtNumE2P(edt_called2);
        Utility.edtNumE2P(edt_catched1);
        Utility.edtNumE2P(edt_catched2);

        mem1 = findViewById(R.id.textView1);
        mem2 = findViewById(R.id.textView2);
        mem3 = findViewById(R.id.textView3);
        mem4 = findViewById(R.id.textView4);
        mem5 = findViewById(R.id.textView5);
        mem6 = findViewById(R.id.textView6);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            member_1 = bundle.getString("member1");
            member_2 = bundle.getString("member2");
            member_3 = bundle.getString("member3");
            member_4 = bundle.getString("member4");
            member_5 = bundle.getString("member5");
            member_6 = bundle.getString("member6");
        } else {
            finish();
        }
        mem1.setText(member_1);
        mem2.setText(member_2);
        mem3.setText(member_3);
        mem4.setText(member_4);
        mem5.setText(member_5);
        mem6.setText(member_6);
        mem1.setTypeface(TF);
        mem2.setTypeface(TF);
        mem3.setTypeface(TF);
        mem4.setTypeface(TF);
        mem5.setTypeface(TF);
        mem6.setTypeface(TF);  //مربوط به نام اعضای گروه ها

        date = findViewById(R.id.txt_Date);
        date.setTypeface(TF);

        date.setText(Utility.numToPersion(Utility.getCurrentShamsidate() + "\n" + Utility.getCurrentTime()));

        scoreAdapter = new ScoreAdapter(this, scoreList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        edt_catched1.setEnabled(false);
        edt_catched2.setEnabled(false);
        btn_add.setEnabled(false);

        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String called_one = edt_called1.getText().toString();
                String called_two = edt_called2.getText().toString();

                if (called_one.equals("") || called_two.equals("")
                        || called_one.equals(null) || called_two.equals(null)
                        || called_one.equals("0") || called_two.equals("0")
                        || called_one.equals("۰") || called_two.equals("۰")
                        || called_one.equals("1") || called_two.equals("1")
                        || called_one.equals("۱") || called_two.equals("۱")
                        || called_one.equals("2") || called_two.equals("2")
                        || called_one.equals("۲") || called_two.equals("۲")) {
                    //Dialog
                    Toast.makeText(context, "خطا!", Toast.LENGTH_LONG).show();

                } else if ((((Integer.parseInt(called_one) * 10) + sumScoreOne) >= 300)
                        && !group_one_catched_7
                        && (Integer.parseInt(called_one) != 7)) {

                    Toast.makeText(context, "گروه یک ۷ دست دا بیگیر", Toast.LENGTH_LONG).show();

                } else if ((((Integer.parseInt(called_two) * 10) + sumScoreTwo) >= 300)
                        && !group_two_catched_7
                        && (Integer.parseInt(called_two) != 7)) {

                    Toast.makeText(context, "گروه دو ۷ دست دا بیگیر", Toast.LENGTH_LONG).show();

                } else {
                    reg_called_one = Integer.parseInt(called_one);
                    reg_called_two = Integer.parseInt(called_two);

                    edt_called1.setEnabled(false);
                    edt_called2.setEnabled(false);

                    edt_catched1.setEnabled(true);
                    edt_catched2.setEnabled(true);

                    btn_OK.setEnabled(false);
                    btn_add.setEnabled(true);

                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                String catched_one = edt_catched1.getText().toString();
                String catched_two = edt_catched2.getText().toString();

                if (catched_one.equals("") || catched_two.equals("")
                        || catched_one.equals(null) || catched_two.equals(null)
                        || catched_one.equals("0") || catched_two.equals("0")
                        || catched_one.equals("۰") || catched_two.equals("۰")) {
                    //Dialog
                    Toast.makeText(context, "خطا!", Toast.LENGTH_LONG).show();

                } else {

                    ScoreData sd = new ScoreData();

                    reg_catched_one = Integer.parseInt(catched_one);
                    reg_catched_two = Integer.parseInt(catched_two);

                    int difference_one = reg_catched_one - reg_called_one;
                    int difference_two = reg_catched_two - reg_called_two;

                    if (difference_one >= 0) {
                        sd.setScore1((reg_called_one * 10) + difference_one);
                    } else {
                        sd.setScore1((reg_called_one * -10));
                    }

                    if (reg_catched_one >= 7 && reg_called_one >= 7 && reg_called_one <= reg_catched_one && !group_one_catched_7) {
                        group_one_catched_7 = true;
                        Toast.makeText(context, "تبریک!!!", Toast.LENGTH_LONG).show();
                    }

                    if (difference_two >= 0) {
                        sd.setScore2((reg_called_two * 10) + difference_two);
                    } else {
                        sd.setScore2((reg_called_two * -10));
                    }

                    if (reg_catched_two >= 7 && reg_called_two >= 7 && reg_called_two <= reg_catched_two && !group_two_catched_7) {
                        group_two_catched_7 = true;
                        Toast.makeText(context, "تبریک!!!", Toast.LENGTH_LONG).show();
                    }

                    sumScoreOne += sd.getScore1();
                    sumScoreTwo += sd.getScore2();

                    sd.setSum_score1(sumScoreOne);
                    sd.setSum_score2(sumScoreTwo);

                    if (sumScoreOne >= 300) {
                        Toast.makeText(context, "برنده بازی: گروه یک", Toast.LENGTH_LONG).show();
                        edt_called1.setEnabled(false);
                        edt_called2.setEnabled(false);
                        edt_catched1.setEnabled(false);
                        edt_catched2.setEnabled(false);
                        btn_OK.setEnabled(false);
                        btn_add.setEnabled(false);

                        // TODO: 13/08/2018

                        try {
                            w1 = URLEncoder.encode(member_1, "utf-8");
                            w2 = URLEncoder.encode(member_2, "utf-8");
                            w3 = URLEncoder.encode(member_3, "utf-8");
                            w_score = sumScoreOne;

                            l1 = URLEncoder.encode(member_4, "utf-8");
                            l2 = URLEncoder.encode(member_5, "utf-8");
                            l3 = URLEncoder.encode(member_6, "utf-8");
                            l_score = sumScoreTwo;


                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        sendData();
                    }
                    if (sumScoreTwo >= 300) {
                        Toast.makeText(context, "برنده بازی: گروه دو", Toast.LENGTH_LONG).show();
                        edt_called1.setEnabled(false);
                        edt_called2.setEnabled(false);
                        edt_catched1.setEnabled(false);
                        edt_catched2.setEnabled(false);
                        btn_OK.setEnabled(false);
                        btn_add.setEnabled(false);

                        //ToDO: 13/08/2018

                        try {
                            w1 = URLEncoder.encode(member_4, "utf-8");
                            w2 = URLEncoder.encode(member_5, "utf-8");
                            w3 = URLEncoder.encode(member_6, "utf-8");
                            w_score = sumScoreTwo;

                            l1 = URLEncoder.encode(member_1, "utf-8");
                            l2 = URLEncoder.encode(member_2, "utf-8");
                            l3 = URLEncoder.encode(member_3, "utf-8");
                            l_score = sumScoreOne;

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        sendData();
                    }

                    scoreList.add(sd);

//                scoreAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(scoreAdapter);

                    edt_catched1.setText("");
                    edt_catched2.setText("");
                    edt_called1.setText("");
                    edt_called2.setText("");

                    edt_called1.setEnabled(true);
                    edt_called2.setEnabled(true);
                    edt_catched1.setEnabled(false);
                    edt_catched2.setEnabled(false);
                    btn_OK.setEnabled(true);
                    btn_add.setEnabled(false);
                }
            }
        });
    }

    public void sendData() {

        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("ارسال اطلاعات")
                .setContentText("ارسال به سرور از طریق اینترنت؟")
                .setConfirmText("ارسال")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(final SweetAlertDialog sDialog) {

                        if (InternetHelper.isOnline(context)) {

                            // reuse previous dialog instance
                            progressFunc(sDialog);

                            //Sending Data Here ...

                            sendingDataAsyncHttpTask task = new sendingDataAsyncHttpTask();
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    G.webservice_url
                                            + "req=create_new_record&record_date=" + Utility.getCurrentShamsidate()
                                            + "&record_time=" + Utility.numToEnglish(Utility.getCurrentTime())
                                            + "&record_w1=" + w1
                                            + "&record_w2=" + w2
                                            + "&record_w3=" + w3
                                            + "&record_l1=" + l1
                                            + "&record_l2=" + l2
                                            + "&record_l3=" + l3
                                            + "&record_w_score=" + w_score
                                            + "&record_l_score=" + l_score);

                            //Receive Boolean should be true after sending successfully ...
                        } else {
                            new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("خطای اتصال")
                                    .setContentText("از اتصال نت اطمینان حاصل نمایید؟")
                                    .setConfirmText("دوباره");
                            Toast.makeText(context, "به شبکه متصل نیستید ، پس از اطمینان از اتصال دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setCancelText("انصراف")
                .show();
    }


    public class sendingDataAsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;
            Integer result = 0;
            HttpURLConnection urlConnection = null;
            String key = params[0];
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                int statusCode = urlConnection.getResponseCode();
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseAdminTaskResult(response.toString());
                    result = 1;
                } else {
                    result = 0;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {

//            received = true;

            if (result == 1 && statusResult == 1) {
                Toast.makeText(context, "Successfully Sent!", Toast.LENGTH_SHORT).show();

            } else if (result != 1 || statusResult != 1) {
                Toast.makeText(context, "Unsuccessful!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void parseAdminTaskResult(String response) {
        try {
            JSONObject object = new JSONObject(response);
            statusResult = object.getInt("success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {

        if (exit) {
//            Intent intent = new Intent(context, MainActivity.class);
//            startActivity(intent);
            SecondActivity.this.finish();
            super.onBackPressed();
        } else {
            Toast.makeText(this, "کلید بازگشت را دوباره لمس کنید ...", Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }

    }


    private void progressFunc(final SweetAlertDialog sDialog) {

        sDialog.setTitleText("Loading").setContentText("درحال ارسال اطلاعات!").changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        sDialog.show();
        sDialog.setCancelable(false);

        new CountDownTimer(800 * 7, 800) {
            public void onTick(long millisUntilFinished) {
                // you can change the progress bar color by ProgressHelper every 800 millis
                i++;
                switch (i) {
                    case 0:
                        sDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                        break;
                    case 1:
                        sDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                        break;
                    case 2:
                        sDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                        break;
                    case 3:
                        sDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                        break;
                    case 4:
                        sDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                        break;
                    case 5:
                        sDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                        break;
                    case 6:
                        sDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                        break;
                }
            }

            public void onFinish() {
                i = -1;
                if (statusResult == 1) {
                    sDialog.setTitleText("ارسال شد!")
                            .setContentText("اطلاعات با موفقیت ارسال شد!")
                            .setConfirmText("باشه")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog hDialog) {

                                    sDialog.cancel();
                                    SecondActivity.this.finish();

//                                    Intent intent = new Intent(context, MainActivity.class);
//                                    startActivity(intent);
                                }
                            }).showCancelButton(false)
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                }else if (statusResult != 1){
                    sDialog.setTitleText("ارسال نشد!")
                            .setContentText("ارسال اطلاعات با شکست مواجه شد!")
                            .setConfirmText("تلاش مجدد")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog hDialog) {
                                    sendData();
                                    sDialog.cancel();

                                }
                            }).showCancelButton(true)
                            .setCancelText("انصراف")
                            .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                }else {
                    progressFunc(sDialog);
                }
            }
        }.start();
    }
}