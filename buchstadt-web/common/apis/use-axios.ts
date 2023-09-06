import axios from "axios";
import { notInterceptUrl } from "@common/utils/interceptor";
import { isAuthed } from "@common/utils/validation";

const axiosInstance = axios.create({
  baseURL: `http://127.0.0.1:9000/api`
});

axiosInstance.interceptors.request.use(
  config => {
    // 已经认证，且 URL 不是 signin、signup、public
    if (
      isAuthed() &&
      !notInterceptUrl(config, {
        fuzzy: ["signin", "signup", "public"]
      })
    ) {
      const token = localStorage.getToken();
      if (token) {
        config.headers["Uid"] = token.id;
        config.headers["Token"] = "Bearer " + token.value;
      }
    }

    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  config => {
    const { data } = config;

    if (
      data.status === 200 &&
      !notInterceptUrl(config.config, {
        fuzzy: ["query"]
      })
    ) {
      ElMessage.success(data.message);
    }

    if (data.status === 400) {
      ElMessage.warning(data.message);
    }

    if (data.status === 500) {
      ElMessage.error(data.message);
    }

    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

export { axiosInstance };
