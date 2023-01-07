package com.example.qrscannerc4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarException;

public class MainActivity extends AppCompatActivity {
    //view object
    private Button buttonScanning;
    private TextView textViewName, textViewClass, textViewId;

    //qrcode scanner object
    private IntentIntegrator qrscan;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.id..layout.activity_main);
        //view object
        buttonScanning = (Button) findViewById(R.id.buttonscan);
        textViewName = (TextView) findViewById(R.id.textViewNama);
        textViewClass = (TextView) findViewById(R.id.textViewKelas);
        textViewId = (TextView) findViewById(R.id.TextViewNim);

        qrscan = new IntentIntegrator(this);
        buttonScanning.setOnClickListener();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null) {
            //jika hasil scanner tidak sama sekali
            if (result.getContents()== null){
                Toast.makeText(this,  "Hasil scan tidak ada", Toast.LENGTH_LONG).show();
            }
            else{

                try {
                    JSONObject obj = new JSONObject(result.getContents());

                    textViewName.setText(obj.getString("nama"));
                    textViewClass.setText(obj.getString("kelas"));
                    textViewId.setText(obj.getString("NIM"));
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void public class QrCodeReaderActivity extends AppCompatActivity {
        private Button scanButton;
        private TextView resultTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_qr_code_reader);

            scanButton = findViewById(R.id.scan_button);
            resultTextView = findViewById(R.id.result_text_view);
        }
    }
    public class QRCodeGenerator {

        private static final int WHITE = 0xFFFFFFFF;
        private static final int BLACK = 0xFF000000;

        public static Bitmap generateQRCode(String phoneNumber) throws WriterException {
            String phoneNumberUri = "tel:" + phoneNumber;
            BitMatrix bitMatrix = new MultiFormatWriter().encode(phoneNumberUri, BarcodeFormat.QR_CODE, 200, 200, null);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = bitMatrix.get(x, y) ? BLACK : WHITE;
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        }
    }
