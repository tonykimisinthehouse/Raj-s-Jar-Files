package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;
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
        this.viewGoodsForSale.setOnItemClickListener(listener);
        this.viewGoodsOnShip.setOnItemClickListener(listener);
    }

    private final AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Get user-selected item
            final ListViewItemDTO item = (ListViewItemDTO) parent.getAdapter().getItem(position);

            // Get checkbox state
            final CheckBox box = view.findViewById(R.id.list_view_item_checkbox);
            final boolean checked = box.isChecked();

            // Toggle checkbox state to on
            item.setChecked(true);
            box.setChecked(true);

            // Determine whether we should buy or sell, based on the parent view
            final boolean buyNotSell = parent == ShipMarketActivity.this.viewGoodsForSale;

            // Display a toast to tell user
            String operationText = buyNotSell ? "Buying " : "Selling ";
            String toastText = operationText + item.getItemText() + "...";
            Toast.makeText(ShipMarketActivity.this, toastText, Toast.LENGTH_SHORT).show();

            // Delay before running operation
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Toggle checkbox state back off
                    item.setChecked(false);
                    box.setChecked(false);

                    // Buy
                    if (buyNotSell) {
                        boolean result = Model.current.getPlayer().makePurchase(item.getGood().getGood(), 1);
                    }

                    // Sell
                    else {
                        boolean result = Model.current.getPlayer().makeSales(item.getGood().getGood(), 1);
                    }

                    // Reload goods
                    ShipMarketActivity.this.updateGoods();
                }
            }, 1000);
        }
    };

    private void updateGoods() {
        // Get latest string representations of goods
        try {
            Player player = Model.current.getPlayer();
            Collection<Item> goodsForSale = player.getPlanet().getMarketplace().getItems();
            Collection<Item> goodsOnShip = player.getShip().getCargoGoods();

            // Split by newlines to get arrays
            List<ListViewItemDTO> listGoodsForSale = new ArrayList<>();
            List<ListViewItemDTO> listGoodsOnShip = new ArrayList<>();

            for (Item item : goodsForSale) {
                // Only add with a positive quantity of item
                if (item.getQuantity() > 0) {
                    ListViewItemDTO view = new ListViewItemDTO();
                    view.setGood(item);
                    listGoodsForSale.add(view);
                }
            }
            for (Item item : goodsOnShip) {
                // Only add with a positive quantity of item
                if (item.getQuantity() > 0) {
                    ListViewItemDTO view = new ListViewItemDTO();
                    view.setGood(item);
                    listGoodsOnShip.add(view);
                }
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
            Log.e("RAJ", e.getMessage());
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
