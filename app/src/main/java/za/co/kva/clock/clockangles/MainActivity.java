package za.co.kva.clock.clockangles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner hourSpinner;
    private Spinner minuteSpinner;
    private CircleView circleView;
    private Clock clock = new Clock();
    private TextView angleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleView = findViewById(R.id.circleViewId);
        angleText = findViewById(R.id.angleText);

        hourSpinner = findViewById(R.id.hour_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(adapter);
        hourSpinner.setSelection(11);
        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                int hours = Integer.parseInt(text);
                clock.hours(hours);
                String angle = getString(R.string.angle_degrees, clock.angle());
                angleText.setText(angle);
                circleView.hourAngle(clock.hourAngle());
                circleView.minuteAngle(clock.minuteAngle());
                circleView.draw();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        minuteSpinner = findViewById(R.id.minutes_spinner);
        ArrayAdapter<CharSequence> minuteAdapter = ArrayAdapter.createFromResource(this, R.array.minutes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minuteSpinner.setAdapter(minuteAdapter);
        minuteSpinner.setSelection(0);
        minuteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String text = adapterView.getItemAtPosition(i).toString();
                int minutes = Integer.parseInt(text);
                clock.minutes(minutes);
                String angle = getString(R.string.angle_degrees, clock.angle());
                angleText.setText(angle);
                circleView.hourAngle(clock.hourAngle());
                circleView.minuteAngle(clock.minuteAngle());
                circleView.draw();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
