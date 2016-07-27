package mtstudio.acs;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.renderscript.Double2;
import android.view.View;
import android.widget.*;


public class ac_start extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private EditText et_bddj;
    private EditText et_gjbt;
    private EditText et_mwqs;

    private TextView tv_rfdl;
    private TextView tv_mtsy;
    private TextView tv_mnsy;
    private TextView tv_hbsj;
    private TextView tv_csys;
    private Button btn_calc;
    private Button btn_reset;

    private Double bddj;
    private Double gjbt;
    private Double rfdl;
    private Double mtsy;
    private Double mnsy;
    private Double csys;
    private Double hssj;
    private Double mwqs;


    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;

    private int status;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        et_bddj = (EditText) findViewById(R.id.et_bddj);
        et_gjbt = (EditText) findViewById(R.id.et_gjbt);
        et_mwqs=(EditText)findViewById(R.id.et_mwqs);
       tv_rfdl = (TextView) findViewById(R.id.tv_rfdl);

        tv_csys = (TextView) findViewById(R.id.tv_csys);
        tv_hbsj = (TextView) findViewById(R.id.tv_hssj);
        tv_mnsy = (TextView) findViewById(R.id.tv_mnsy);
        tv_mtsy = (TextView) findViewById(R.id.tv_mtsy);


        btn_calc = (Button) findViewById(R.id.btn_calc);
        btn_reset=(Button)findViewById(R.id.btn_reset);

        cb3=(CheckBox)findViewById(R.id.cb3);
        cb4=(CheckBox)findViewById(R.id.cb4);
        cb5=(CheckBox)findViewById(R.id.cb5);


        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cb3.isChecked()){
                    cb4.setChecked(false);
                    cb5.setChecked(false);
                    status=3;

                }
            }
        });

        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cb4.isChecked()){
                    cb3.setChecked(false);
                    cb5.setChecked(false);
                    status=4;

                }
            }
        });

        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cb5.isChecked()){
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                    status=5;

                }
            }
        });

        btn_calc.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        cb3.setChecked(true);

        et_mwqs.setSelection(et_mwqs.getText().length());
        et_bddj.setSelection(et_bddj.getText().length());
        et_gjbt.setSelection(et_gjbt.getText().length());


    }
 private Boolean isEmpty(EditText et) {
     return (et.getText().toString().equals(""))?false:true;
 }
private void doCalc() {


        bddj = Double.valueOf(et_bddj.getText().toString());
        gjbt = Double.valueOf(et_gjbt.getText().toString());
        mwqs=Double.valueOf(et_mwqs.getText().toString());

        switch (status) {
            case 3:
                rfdl = Double.valueOf(3 * 4);
                break;
            case 4:
                rfdl = Double.valueOf(4 * 4);
                break;
            case 5:
                rfdl = Double.valueOf(5 * 4);
                break;
        }

        tv_rfdl.setText(String.valueOf(rfdl));

        mtsy=(bddj+gjbt)*rfdl;

        tv_mtsy.setText(String.valueOf(mtsy));

        mnsy=mtsy*365;
        tv_mnsy.setText(String.valueOf(mnsy));

        hssj=(status*1000*mwqs)/mnsy;
        tv_hbsj.setText(String.valueOf(hssj));

        csys=mnsy*(25-hssj)+5*365*mtsy*bddj;
        tv_csys.setText(String.valueOf(csys));





}
    @Override
    public void onClick(View view) {


            switch (view.getId()) {
                case R.id.btn_calc:
                    if (isEmpty(et_bddj) && isEmpty(et_gjbt)) {
                        doCalc();
                    } else {
                        Toast.makeText(this, "文本值不能为空!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_reset:
                    cb3.setChecked(true);
                    et_bddj.setText("0.56");
                    et_gjbt.setText("0.42");
                    tv_rfdl.setText("0");
                    tv_mtsy.setText("0");
                    tv_mnsy.setText("0");
                    tv_hbsj.setText("0");
                    tv_csys.setText("0");
                    et_mwqs.setText("10");
                    et_mwqs.setSelection(et_mwqs.getText().length());
                    et_bddj.setSelection(et_bddj.getText().length());
                    et_gjbt.setSelection(et_gjbt.getText().length());
                    break;

            }
    }
}
