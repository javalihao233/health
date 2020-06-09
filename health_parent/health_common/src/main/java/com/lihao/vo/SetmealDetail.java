package com.lihao.vo;

import com.lihao.domain.CheckGroup;
import com.lihao.domain.CheckItem;
import com.lihao.domain.Setmeal;

public class SetmealDetail {
    private CheckItem checkItem;

    private CheckGroup checkGroup;

    private Setmeal setmeal;

    public CheckItem getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(CheckItem checkItem) {
        this.checkItem = checkItem;
    }

    public CheckGroup getCheckGroup() {
        return checkGroup;
    }

    public void setCheckGroup(CheckGroup checkGroup) {
        this.checkGroup = checkGroup;
    }

    public Setmeal getSetmeal() {
        return setmeal;
    }

    public void setSetmeal(Setmeal setmeal) {
        this.setmeal = setmeal;
    }
}
