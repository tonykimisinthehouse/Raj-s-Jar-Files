package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.helper.ListViewItemCheckboxBaseAdapter;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.helper.ListViewItemDTO;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels.ShipMarketViewModel;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;

public class ShipMarketActivity extends AppCompatActivity {

    /**
     * View model for ShipMarket activity
     */
    private ShipMarketViewModel viewModel;

    private ListView viewGoodsForSale;
    private ListView viewGoodsOnShip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_market);

        this.viewModel = ViewModelProviders.of(this).get(ShipMarketViewModel.class);

        this.assignViews();
        this.updateGoods();
    }

    private void assignViews() {
        this.viewGoodsForSale = findViewById(R.id.selector_goods_for_sale);
        this.viewGoodsOnShip = findViewById(R.id.selector_goods_on_ship);
        this.viewGoodsForSale.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get user-selected item
                ListViewItemDTO item = (ListViewItemDTO) parent.getAdapter().getItem(position);

                // Get checkbox state
                CheckBox box = (CheckBox) view.findViewById(R.id.list_view_item_checkbox);
                boolean checked = box.isChecked();

                // Toggle checkbox state
                item.setChecked(!checked);
                box.setChecked(!checked);
            }
        });
    }

    private void updateGoods() {
        // Get latest string representations of goods
        try {
            Player player = Model.current.getPlayer();
            String goodsForSale = player.getPlanet().getMarketplace().toString();
            String goodsOnShip = player.getShip().getGoods();

            // Split by newlines to get arrays
            List<ListViewItemDTO> listGoodsForSale = new ArrayList<>();
            List<ListViewItemDTO> listGoodsOnShip = new ArrayList<>();
            for (String desc : goodsForSale.split("\n")) {
                ListViewItemDTO item = new ListViewItemDTO();
                item.setItemText(desc);
                listGoodsForSale.add(item);
            }
            for (String desc : goodsOnShip.split("\n")) {
                ListViewItemDTO item = new ListViewItemDTO();
                item.setItemText(desc);
                listGoodsOnShip.add(item);
            }

            final ListViewItemCheckboxBaseAdapter adapterGoodsForSale = new
                    ListViewItemCheckboxBaseAdapter(this, listGoodsForSale);
            final ListViewItemCheckboxBaseAdapter adapterGoodsOnShip = new
                    ListViewItemCheckboxBaseAdapter(this, listGoodsOnShip);

            adapterGoodsForSale.notifyDataSetChanged();
            adapterGoodsOnShip.notifyDataSetChanged();

            // Set data on views
            this.viewGoodsForSale.setAdapter(adapterGoodsForSale);
            this.viewGoodsOnShip.setAdapter(adapterGoodsOnShip);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void buyGoods(View v) {
        // TODO get list of checked goods
        // TODO tell market to sell
    }

    public void sellGoods(View v) {
        // TODO get list of checked goods
        // TODO tell ship to sell
    }

}
