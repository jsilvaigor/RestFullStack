package us.antenado.restnews;

import us.antenado.restnews.rest.dto.SimpleNewsDTO;

/**
 * Created by isilva on 10/21/17.
 */

public interface OnListItemInteractor {
    void onListFragmentInteraction(SimpleNewsDTO item, InteractionType interactionType);
}
