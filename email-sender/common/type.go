package common

// Weather site data
type Weather struct {
	City     string // 城市
	Temp     string // 温度
	Weather  string // 天气
	Air      string // 空气
	Humidity string // 湿度
	Wind     string // 风向
	Limit    string // 限行
	Note     string // 提示
}

// One site info
type One struct {
	Date     string
	ImgURL   string
	Sentence string
}

// English info
type English struct {
	ImgURL   string
	Sentence string
}

// Poem info
type Poem struct {
	Title   string   `json:"title"`
	Dynasty string   `json:"dynasty"`
	Author  string   `json:"author"`
	Content []string `json:"content"`
}

// PoemRes response data
type PoemRes struct {
	Status string `json:"status"`
	Data   struct {
		Origin Poem `json:"origin"`
	} `json:"data"`
}

// Wallpaper data
type Wallpaper struct {
	Title  string
	ImgURL string
}
