/*
* Copyright 2023 Taleemabad (https://www.taleemabad.com/)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package utils

import java.io.File

private val desktopDir = System.getProperty("user.home") + File.separator + "Desktop"
private val appBundleDirectory = desktopDir + File.separator + "Taleemabad"
val lpsDirectory = appBundleDirectory + File.separator + "LPs"
val trainingDirectory = appBundleDirectory + File.separator + "Trainings"


//val downloadDirectory = lpsDirectory + File.separator + "Videos" + File.separator // for Videos
val downloadDirectory = lpsDirectory + File.separator + "Images" + File.separator  // for Images
val compressDirectory = lpsDirectory + File.separator + "Compressed" + File.separator
val encryptedDirectory = lpsDirectory + File.separator + "Encrypted" + File.separator
val csvz = lpsDirectory + File.separator + "CSV" + File.separator
const val trainingData = "src/main/resources/assets/training_data.json"
const val lpData = "src/main/resources/assets/lp_lps.json"
const val lpImages = "src/main/resources/assets/lp_lps_images.json"
const val lpAudios = "src/main/resources/assets/lp_lps_audios.json"
const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"