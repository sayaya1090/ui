import * as mdc from '@material/web/all';
import {MdBadge} from '@material/web/labs/badge/badge';
import {MdElevatedCard} from '@material/web/labs/card/elevated-card';
import {MdFilledCard} from '@material/web/labs/card/filled-card';
import {MdOutlinedCard} from '@material/web/labs/card/outlined-card';
import {MdNavigationBar} from '@material/web/labs/navigationbar/navigation-bar';
import {MdNavigationDrawer} from '@material/web/labs/navigationdrawer/navigation-drawer';
import {MdNavigationTab} from '@material/web/labs/navigationtab/navigation-tab';
import {MdOutlinedSegmentedButton} from '@material/web/labs/segmentedbutton/outlined-segmented-button';
import {MdOutlinedSegmentedButtonSet} from '@material/web/labs/segmentedbuttonset/outlined-segmented-button-set';

window.mdc = {}
Object.assign(window.mdc, mdc,{
    MdBadge,
    MdElevatedCard,
    MdFilledCard,
    MdOutlinedCard,
    MdNavigationBar,
    MdNavigationDrawer,
    MdNavigationTab,
    MdOutlinedSegmentedButton,
    MdOutlinedSegmentedButtonSet
})