package com.example.firebase_auth.Medical;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import com.example.firebase_auth.R;


public class RequiredMedicines extends AppCompatActivity {

    /**
     * index for array details and total medicines
     */
    byte index = 0, TOTAL_MEDICINES;
    /**
     * gestureDetectorCompat variable
     */
    private GestureDetectorCompat gestureDetectorCompat;
    /**
     * medicineNames, descriptions, images array
     */
    private int[] medicineNames, descriptions, images;
    /**
     * medicineName medicineDescription medicineIndes textView variables
     */
    private TextView medicineName, medicineDescription, medicineIndex;
    /**
     * imageView variable for medicines image view
     */
    private ImageView medicineImage;

    /**
     * override onCreate method
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setTitle(getResources().getStringArray(R.array.medicalProblems)[MedicinesController.index]);

        /** initializing medicines descriptions and images variables */
        medicineNames = MedicinesController.getMedicines();
        descriptions = MedicinesController.getDescriptions();
        images = MedicinesController.getImagesId();

        /** totalMedicines byte variable */
        TOTAL_MEDICINES = (byte) medicineNames.length;

        /** settingUp layout content  */
        setContentView(R.layout.activity_required_medicines);
        /** initializing gestures */
        gestureDetectorCompat = new GestureDetectorCompat(this, new Gesture());

        /** settingUp setOnClickMethod for buyOnline method
         * @param OnClickListener
         * */


        /** initializing medicineName medicineDescription medicineImage and medicineIndex variables */
        medicineName = (TextView) findViewById(R.id.medicineName);
        medicineDescription = (TextView) findViewById(R.id.medicineDescription);
        medicineImage = (ImageView) findViewById(R.id.medicineImage);
        medicineIndex = (TextView) findViewById(R.id.indexView);

        /** calling method fill data */
        fillData(index);

    }

    /** buyOnline method
     * @param view
     * */


    /**
     * onTouchEvent
     *
     * @param motionEvent
     * @return condition if is possible to apply gesture
     */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.gestureDetectorCompat.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    /**
     * fillData method
     *
     * @param i
     */
    void fillData(byte i) {
        medicineName.setText(medicineNames[i]);
        medicineDescription.setText(descriptions[i]);
        medicineImage.setImageResource(images[i]);

        medicineIndex.setText((i + 1) + "/" + TOTAL_MEDICINES);
    }

    /**
     * gestures
     *
     * @extends SimpleOnGestureListener
     */
    class Gesture extends GestureDetector.SimpleOnGestureListener {

        /**
         * override method onFling
         *
         * @param e1
         * @param e2
         * @param velocityX
         * @param velocityY
         * @return true
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (e2.getX() < e1.getX()) {
                if (index < TOTAL_MEDICINES - 1) {
                    index++;
                    fillData(index);
                }
            } else if (e2.getX() > e1.getX()) {
                if (index > 0) {
                    index--;
                    fillData(index);
                }
            }

            return true;
        }
    }
}
