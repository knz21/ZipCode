package com.knz21.zipcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.knz21.zipcode.di.ActivityComponent;
import com.knz21.zipcode.di.ActivityModule;
import com.knz21.zipcode.domain.ZipCode;
import com.knz21.zipcode.store.Address;
import com.knz21.zipcode.view.adapter.AddressListAdapter;
import dagger.Lazy;

import javax.inject.Inject;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;
    @Inject
    Lazy<ZipCode> zipCode;
    @BindView(R.id.address_list)
    ListView addressList;
    @BindView(R.id.zip_code_edit)
    TextView zipCodeEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activityComponent = ((ZipCodeApp) getApplication()).getComponent()
                .newActivityComponent(new ActivityModule());
        activityComponent.inject(this);
        addressList.setAdapter(new AddressListAdapter());
        search();
    }

    @OnClick(R.id.search_button)
    void search() {
        zipCode.get().search(zipCodeEdit.getText().toString(), this::update, this::noticeError);
    }

    @OnClick(R.id.clear_button)
    void clear() {
        zipCode.get().clear(this::onClear);
    }

    private void update(List<Address> addresses) {
        ((AddressListAdapter) addressList.getAdapter()).update(addresses);
    }

    private void noticeError(Throwable t) {
        Toast.makeText(this, getString(R.string.toast_failure), Toast.LENGTH_SHORT).show();
    }

    private void onClear() {
        ((AddressListAdapter) addressList.getAdapter()).clear();
    }
}
