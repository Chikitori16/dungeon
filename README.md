# dungeon
Chào bạn, đây là file README.md được tối chuẩn hóa kỹ thuật. File này không chỉ giúp người dùng mà còn giúp các AI Agent khác (hoặc lập trình viên kế nhiệm) hiểu rõ cấu trúc logic, cách thức vận hành và các điểm mở rộng (Extension Points) của AeckDungeon.

🛡️ AeckDungeon - Advanced Dungeon System for aeck.online

AeckDungeon là một Plugin quản lý Hầm ngục (Dungeon) chuyên sâu được thiết kế cho các máy chủ Minecraft RPG/Survival hiện đại. Plugin tối ưu hóa hiệu suất bằng cách sử dụng xử lý bất đồng bộ (Async) và hệ thống Scripting linh hoạt cho quái vật.

📌 Tổng quan hệ thống (Technical Overview)

Package Name: vn.aeck.dungeon

Core Logic: Quản lý không gian (Instances), hệ thống Party, và xử lý quái vật/boss tùy chỉnh thông qua tệp cấu hình YAML.

Dependencies: FastAsyncWorldEdit (FAWE), MMOItems, MythicLib, Vault.

📂 Cấu trúc thư mục (Directory Structure)

Plaintext



/plugins/AeckDungeon/

├── hamnguc.yml # Định nghĩa Dungeon, GUI và cấu hình Boss

├── quaivat.yml # Thông số Mob/Boss, Trang bị & Tỷ lệ rơi đồ

├── loots.yml # Pool vật phẩm ngẫu nhiên dựa trên Trọng số (Weight)

├── skills/ # Thư mục chứa các tệp .yml định nghĩa kỹ năng Scripting

└── dungeon_data/ # Cache lưu trữ Indexing (Tọa độ Sign/Chest trong Schematic)

⚙️ Các tính năng cốt lõi (Core Features)

1. Hệ thống Instance & Scaling

Phân luồng không gian: Sử dụng FAWE để dán (Paste) hầm ngục tại các tọa độ cách xa nhau (mặc định 2000 block) trong một World riêng biệt.

Scaling Algorithm: Máu và Sát thương của quái vật tự động tăng theo số lượng thành viên trong Party:

Healthfinal

​=Base×(1+(PartySize−1)×0.5)

Cleanup: Tự động xóa (Set AIR) khu vực hầm ngục sau khi hoàn thành hoặc Party thất bại để giải phóng RAM/ROM.

2. Thuật toán Indexing (Zero-Lag Scanning)

Plugin không quét block khi người chơi đang tham gia. Thay vào đó, nó thực hiện Indexing khi khởi động:

Tìm kiếm các Sign chứa {monster:<id>} hoặc Chest chứa Nametag {item:ngaunhien}.

Lưu trữ Relative Offset (Tọa độ tương đối so với điểm dán) vào bộ nhớ.

Khi dán hầm ngục, chỉ cần truy cập Map để Spawn quái vật và rải đồ.

3. Hệ thống Kỹ năng (Scriptable Skill System)

Hệ thống Skill được chia thành 2 phần:

Java Core: SkillBase.java định nghĩa logic thực thi (AOE, Summon, Trap...).

YAML Logic: Người dùng có thể tạo kỹ năng mới trong thư mục skills/ bằng cách thay đổi các tham số damage, radius, cooldown.

4. Thuật toán Loot theo Trọng số (Weighted Scatter)

Weight Logic: Tỷ lệ xuất hiện vật phẩm tỉ lệ thuận với trọng số trong loots.yml.

Scatter Logic: Vật phẩm được rải vào các ô ngẫu nhiên trong rương, đảm bảo tính thẩm mỹ và không lấp đầy rương (tối đa 2-6 món/rương).

🛠️ Hướng dẫn dành cho AI/Developer (Technical Implementation)

Định dạng vật phẩm (Item Parsing)

Plugin hỗ trợ cả vật phẩm Vanilla và MMOItems thông qua cú pháp:

Vanilla: DIAMOND, IRON_SWORD

MMOItems: mmoitem:CATEGORY:ID (Ví dụ: mmoitem:SWORD:EXCALIBUR)

Mở rộng Kỹ năng (Adding New Skills)

Để thêm một loại kỹ năng mới, hãy thực hiện theo các bước:

Tạo Class mới kế thừa SkillBase.

Đăng ký loại kỹ năng trong SkillManager thông qua tham số type trong YAML.

Triển khai logic thực thi trong hàm cast(LivingEntity caster).

📜 Lệnh & Quyền hạn (Commands)

/hamnguc: Mở GUI quản lý Dungeon (Yêu cầu quyền: aeckdungeon.use).

/party create | join <ID>: Quản lý đội nhóm.

/hamnguc reload: Tải lại toàn bộ cấu hình và làm mới bộ nhớ Cache Indexing.

Note for Agents: Luôn ưu tiên xử lý dữ liệu qua DungeonManager để đảm bảo tính đồng bộ giữa các Instance. Tuyệt đối không thay đổi Block trực tiếp mà không thông qua FAWE API để tránh gây treo Server.
