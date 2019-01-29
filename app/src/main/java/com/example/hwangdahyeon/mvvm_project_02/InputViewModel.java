package com.example.hwangdahyeon.mvvm_project_02;

import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.RatingBar;

public class InputViewModel implements BaseViewModel {

    public  final int MAX_SCORE = 5;

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableInt score = new ObservableInt();
    public final ObservableInt maxScore = new ObservableInt();
    public final ObservableField<String> textScore = new ObservableField<>();
    public final ObservableBoolean isValid = new ObservableBoolean();

    @Override
    public void onCreate() {
        maxScore.set(MAX_SCORE);
        score.set(0);
        textScore.set(score.get() + " / " + maxScore.get());
        isValid.set(false);

        name.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                validation() ;
            }
        });

        email.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                validation();
            }
        });
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }
    private void validation() {
        boolean isValidName = !TextUtils.isEmpty(name.get());
        boolean isValidEmail = !TextUtils.isEmpty(email.get()) && Patterns.EMAIL_ADDRESS.matcher(email.get()).matches();
        boolean isValidScore = score.get() > 0;
        isValid.set(isValidName && isValidEmail && isValidScore);
    }

    public RatingBar.OnRatingBarChangeListener scoreChangeListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            score.set((int) v);
            textScore.set(score.get() + " / " + maxScore.get());
            validation();
        }
    };
}
