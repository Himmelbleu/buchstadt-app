export async function query(params?: { buchType?: string; buchId?: number }) {
  const {
    data: { data }
  } = await axiosInstance.get("/buch/comment/query", { params });
  return data;
}

export async function insert(params: { content: string; type: string; buchId: number }) {
  const { data } = await axiosInstance.post("/buch/comment/insert", params);
  if (data.status == 200) {
    ElMessage.success(data.message);
  }
  return data;
}