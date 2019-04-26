package edu.gatech.cs2340.rajsjarfiles.spacetrader.helper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;

/**
 * @see "https://www.dev2qa.com/android-custom-listview-with-checkbox-example/"
 */
public class ListViewItemCheckboxBaseAdapter extends BaseAdapter {

    private final List<ListViewItemDTO> listViewItemDtoList;

    private Context ctx;

    /**
     * Adapter for list view Item checkbox
     * @param ctx context
     * @param listViewItemDtoList lsit
     */
    public ListViewItemCheckboxBaseAdapter(
            Context ctx, List<ListViewItemDTO> listViewItemDtoList) {
        this.ctx = ctx;
        this.listViewItemDtoList = listViewItemDtoList;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (listViewItemDtoList != null) {
            ret = listViewItemDtoList.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int itemIndex) {
        Object ret = null;
        if (listViewItemDtoList != null) {
            ret = listViewItemDtoList.get(itemIndex);
        }
        return ret;
    }

    @Override
    public long getItemId(int itemIndex) {
        return itemIndex;
    }

    @Override
    public View getView(int itemIndex, View convertView, ViewGroup viewGroup) {

        View convertViewPointer = convertView;
        ListViewItemViewHolder viewHolder = null;

        if (convertViewPointer != null) {
            viewHolder = (ListViewItemViewHolder) convertViewPointer.getTag();
        } else {
            convertViewPointer = View.inflate(
                    ctx,
                    R.layout.activity_list_view_with_checkbox_item,
                    null);

            CheckBox listItemCheckbox = convertViewPointer.findViewById(
                            R.id.list_view_item_checkbox);

            TextView listItemText = convertViewPointer.findViewById(
                            R.id.list_view_item_text);

            viewHolder = new ListViewItemViewHolder(convertViewPointer);

            viewHolder.setItemCheckbox(listItemCheckbox);

            viewHolder.setItemTextView(listItemText);

            convertViewPointer.setTag(viewHolder);
        }

        ListViewItemDTO listViewItemDto = listViewItemDtoList.get(itemIndex);
        viewHolder.getItemCheckbox().setChecked(listViewItemDto.isChecked());
        viewHolder.getItemTextView().setText(listViewItemDto.getItemText());

        return convertViewPointer;
    }
}
