package component.template

import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.dom.html.ReactHTML.span
import react.useState
import com.sovostyanov.application.common.Item
import com.sovostyanov.application.config.Config
import react.router.dom.Link

external interface ElementInListProps<E> : Props {
    var element: E
}

external interface EditAddProps<E> : Props {
    var saveElement: (E) -> Unit
}

external interface EditItemProps<E> : Props {
    var item: Item<E>
    var saveElement: (E) -> Unit
}

inline fun <reified E : Any> restList(
    cElementInList: FC<ElementInListProps<E>>,
    cAddItem: FC<EditAddProps<E>>,
    cEditItem: FC<EditItemProps<E>>,
    displayName: String = "ListContainer"
) = FC(displayName) { props: RestContainerChildProps<E> ->
    var editedIndex by useState(-1)
    cAddItem {
        saveElement = { props.addElement(it) }
    }
    val editedItem = props.items.getOrNull(editedIndex)
    if (editedItem != null)
        cEditItem {
            item = editedItem
            saveElement = { props.updateItem(Item(it, editedItem.id)) }
            key = editedItem.id
        }
    ol {
        props.items.forEachIndexed { index, item ->
            li {
                Link{
                    to = "${item.id}/lessons/"
                    cElementInList {
                        element = item.elem
                    }
                }

                span {
                    +" ✂ "
                    onClick = {
                        props.deleteItem(item.id)
                    }
                }
                span {
                    +" ✎ "
                    onClick = {
                        editedIndex = index
                    }
                }
            }
        }
    }
}

