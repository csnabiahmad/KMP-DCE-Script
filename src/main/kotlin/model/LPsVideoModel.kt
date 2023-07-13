package model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class LPsVideoModel : ArrayList<LPsVideoModel.LPsVideoModelItem>(){
    @Serializable
    data class LPsVideoModelItem(
        @SerialName("uuid")
        var uuid: String?, // eab7a2b8-ed39-4997-a763-4df1a032ff0d
        @SerialName("videoLink")
        var videoLink: List<String>
    )
}