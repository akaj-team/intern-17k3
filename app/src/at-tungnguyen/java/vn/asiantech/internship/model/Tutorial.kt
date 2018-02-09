package vn.asiantech.internship.model

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 02/02/2018.
 */
data class Tutorial(private var color: String, private var title: String, private var description: String, private var image: Int) {
    constructor() : this(color = "", title = "", description = "", image = 0)
}
