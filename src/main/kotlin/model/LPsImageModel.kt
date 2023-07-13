package model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class LPsImageModel : ArrayList<LPsImageModel.LPsImageModelItem>(){
    @Serializable
    data class LPsImageModelItem(
        @SerialName("link")
        var link: String? // https://lmsreborn-lp-images.s3.ap-south-1.amazonaws.com/ab320c2a-8fff-11ec-b8d2-81ab5dd54aa1.jpg
    )
}